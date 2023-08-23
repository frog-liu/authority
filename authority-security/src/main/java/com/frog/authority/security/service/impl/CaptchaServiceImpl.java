package com.frog.authority.security.service.impl;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import com.frog.authority.common.base.constant.RedisKey;
import com.frog.authority.common.base.enums.ImageType;
import com.frog.authority.common.base.exception.BusinessException;
import com.frog.authority.common.base.util.Assert;
import com.frog.authority.common.redis.util.RedisUtils;
import com.frog.authority.security.config.CaptchaProperties;
import com.frog.authority.security.constant.Message;
import com.frog.authority.security.exception.CaptchaException;
import com.frog.authority.security.service.ICaptchaService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author frog
 */
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements ICaptchaService {

    private static final String CAPTCHA_KEY = "captchaKey";

    private static final String CAPTCHA = "captcha";

    private static final String NO_CACHE = "No-cache";

    private final RedisUtils redisUtils;

    private final CaptchaProperties captchaProperties;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取前端生成的验证码key uuid并校验不能为空
        String captchaKey = request.getParameter(CAPTCHA_KEY);
        Assert.notEmpty(BusinessException.class, captchaKey, Message.Captcha.KEY_NOT_EMPTY);
        // 根据验证码配置生成验证码
        setHeader(response, captchaProperties.getImageType());
        AbstractCaptcha captcha = createCaptcha(captchaProperties);
        String captchaCode = captcha.getCode().toLowerCase();
        Long expireTime = captchaProperties.getExpireTime();
        // 验证码存放到redis缓存中,并设置过期时间
        redisUtils.set(RedisKey.Captcha.KEY + captchaKey, captchaCode, expireTime, TimeUnit.SECONDS);
        // 验证码写入返回中
        captcha.write(response.getOutputStream());
    }

    @Override
    public void check(HttpServletRequest request, HttpServletResponse response) {
        String captchaKey = request.getParameter(CAPTCHA_KEY);
        String captcha = request.getParameter(CAPTCHA);
        Assert.notEmpty(BusinessException.class, captchaKey, Message.Captcha.KEY_NOT_EMPTY);
        Assert.notEmpty(BusinessException.class, captcha,  Message.Captcha.NOT_EMPTY);

        String rightCaptcha = String.valueOf(redisUtils.get(RedisKey.Captcha.KEY + captchaKey));
        if (StringUtils.isBlank(rightCaptcha)) {
            throw new CaptchaException(Message.Captcha.EXPIRED);
        }
        if (!StringUtils.equalsIgnoreCase(rightCaptcha, captcha)) {
            throw new CaptchaException(Message.Captcha.MISTAKE);
        }
    }

    private AbstractCaptcha createCaptcha(CaptchaProperties captchaProperties) {
        int width = captchaProperties.getWidth();
        int height = captchaProperties.getHeight();
        int length = captchaProperties.getLength();
        ImageType type = captchaProperties.getImageType();
        switch (type) {
            case GIF:
                return CaptchaUtil.createGifCaptcha(width, height, length);
            default:
                return CaptchaUtil.createCircleCaptcha(width, height, length, length);
        }
    }

    private void setHeader(HttpServletResponse response, ImageType imageType) {
        // 设置返回对象媒体类型
        switch (imageType) {
            case GIF:
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            default:
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        // 禁止缓存
        response.setHeader(HttpHeaders.CACHE_CONTROL, NO_CACHE);
        response.setHeader(HttpHeaders.PRAGMA, NO_CACHE);
        response.setDateHeader(HttpHeaders.EXPIRES, 0l);
    }

}
