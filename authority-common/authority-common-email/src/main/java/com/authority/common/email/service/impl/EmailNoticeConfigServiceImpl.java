package com.authority.common.email.service.impl;

import com.authority.common.email.domain.EmailNoticeConfig;
import com.authority.common.email.enums.NoticeType;
import com.authority.common.email.mapper.EmailNoticeConfigMapper;
import com.authority.common.email.service.IEmailNoticeConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.common.core.enums.ExceptionType;
import com.frog.authority.common.core.util.Assert;
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
        Assert.notNull(ExceptionType.ILLEGAL_ARGUMENT, type, "邮件配置类型不能为空!");
        return this.lambdaQuery()
                   .eq(EmailNoticeConfig::getNoticeType, type)
                   .one();
    }
}
