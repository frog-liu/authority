package com.frog.authority.common.email.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.frog.authority.common.email.domain.EmailNoticeConfig;
import com.frog.authority.common.email.enums.NoticeType;
import com.frog.authority.common.email.service.IEmailNoticeConfigService;
import com.frog.authority.common.email.service.IEmailService;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author frog
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements IEmailService {

    private final JavaMailSender mailSender;

    private final IEmailNoticeConfigService emailNoticeConfigService;

    @Override
    public void sendFile(NoticeType noticeType, String filePath) {
        sendHtml(noticeType, StringPool.EMPTY, filePath);
    }

    @Override
    public void sendFiles(NoticeType noticeType, Map<String, File> attachments) {
        sendHtml(noticeType, StringPool.EMPTY, attachments);
    }

    @Override
    public void sendHtml(NoticeType noticeType, String htmlText, String filePath) {
        File file = new File(filePath);
        Map<String, File> attachments = new HashMap<>();
        attachments.put(file.getName(), file);
        sendHtml(noticeType, htmlText, attachments);
    }

    @Override
    public void sendHtml(NoticeType noticeType, String htmlText, Map<String, File> attachments) {
        EmailNoticeConfig emailNoticeConfig = emailNoticeConfigService.findByNoticeType(noticeType);
        if (ObjectUtil.isNotNull(emailNoticeConfig)) {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            try {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setSubject(emailNoticeConfig.getSubject());
                mimeMessageHelper.setFrom(emailNoticeConfig.getFrom());
                mimeMessageHelper.setTo(splitAddress(emailNoticeConfig.getTo()));
                mimeMessageHelper.setCc(splitAddress(emailNoticeConfig.getCc()));
                mimeMessageHelper.setText(htmlText, true);
                if (CollectionUtils.isEmpty(attachments)) {
                    for (Map.Entry<String, File> entry : attachments.entrySet()) {
                        mimeMessageHelper.addAttachment(entry.getKey(), entry.getValue());
                    }
                }
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                log.error("邮件发送失败:{}", e.getMessage(), e);
            }
        }
    }

    private String[] splitAddress(String address) {
        if (StringUtils.isNotBlank(address)) {
            return address.split("[,;\\s+]");
        }
        return new String[0];
    }
}
