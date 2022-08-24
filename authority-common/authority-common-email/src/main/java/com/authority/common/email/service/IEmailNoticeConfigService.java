package com.authority.common.email.service;

import com.authority.common.email.domain.EmailNoticeConfig;
import com.authority.common.email.enums.NoticeType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author liuhuan
 */
public interface IEmailNoticeConfigService extends IService<EmailNoticeConfig> {

    /**
     * 根据通知类型查询邮件配置信息
     * @param type 通知类型
     * @return 邮件配置信息
     */
    EmailNoticeConfig findByNoticeType(NoticeType type);
}
