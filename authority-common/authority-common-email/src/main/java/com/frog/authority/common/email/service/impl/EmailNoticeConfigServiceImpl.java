package com.frog.authority.common.email.service.impl;

import com.frog.authority.common.base.exception.BusinessException;
import com.frog.authority.common.email.domain.EmailNoticeConfig;
import com.frog.authority.common.email.enums.NoticeType;
import com.frog.authority.common.email.mapper.EmailNoticeConfigMapper;
import com.frog.authority.common.email.service.IEmailNoticeConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.common.base.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author frog
 */
@Slf4j
@Service
public class EmailNoticeConfigServiceImpl extends ServiceImpl<EmailNoticeConfigMapper, EmailNoticeConfig> implements IEmailNoticeConfigService {

    @Override
    public EmailNoticeConfig findByNoticeType(NoticeType type) {
        Assert.notNull(BusinessException.class, type, "邮件配置类型不能为空!");
        return this.lambdaQuery()
                   .eq(EmailNoticeConfig::getNoticeType, type)
                   .one();
    }
}
