package com.frog.authority.security.config;

import com.frog.authority.common.base.enums.ImageType;
import com.frog.authority.security.enums.CaptchaType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

/**
 * 验证码配置
 * @author frog
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class CaptchaProperties {

    /**
     * 过期时间
     */
    @Value("${captcha.expiration.time:60}")
    private Long expireTime;

    /**
     * 图片类型
     */
    private ImageType imageType = ImageType.PNG;

    /**
     * 图片宽度:px
     */
    @Value("${captcha.width:130}")
    private Integer width;

    /**
     * 图片高度:px
     */
    @Value("${validate.height:48}")
    private Integer height;

    /**
     * 验证码长度
     */
    @Value("${validate.length:4}")
    private Integer length;

    /**
     * 验证码类型
     */
    @Value("${validate.type:CHARACTER}")
    private CaptchaType type;

}
