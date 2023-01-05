package com.authority.common.email.service;

import com.authority.common.email.enums.NoticeType;

import java.io.File;
import java.util.Map;

/**
 * @author frog
 */
public interface IEmailService {

    /**
     * 发送附件,不包含正文
     * @param noticeType 通知类型
     * @param filePath 附件路径
     */
    void sendFile(NoticeType noticeType, String filePath);

    /**
     * 发送多个附件,不包含正文
     * @param noticeType 通知类型
     * @param attachments 附件列表
     */
    void sendFiles(NoticeType noticeType, Map<String, File> attachments);

    /**
     * 发送邮件(html格式)
     * @param noticeType 通知类型
     * @param htmlText 邮件正文(html格式)
     * @param filePath 附件路径
     */
    void sendHtml(NoticeType noticeType, String htmlText, String filePath);

    /**
     * 发送邮件(html格式)
     * @param noticeType 通知类型
     * @param htmlText 邮件正文(html格式)
     * @param attachments 附件
     */
    void sendHtml(NoticeType noticeType, String htmlText, Map<String, File> attachments);
}
