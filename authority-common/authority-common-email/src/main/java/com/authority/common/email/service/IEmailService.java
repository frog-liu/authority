package com.authority.common.email.service;

import com.authority.common.email.enums.NoticeType;

import java.io.File;
import java.util.Map;

/**
 * @author frog
 */
public interface IEmailService {

    /**
     * 发送邮件(html格式)
     * @param noticeType 通知类型
     * @param htmlText 邮件正文(html格式)
     * @param attachments 附件
     */
    void sendHtml(NoticeType noticeType, String htmlText, Map<String, File> attachments);
}
