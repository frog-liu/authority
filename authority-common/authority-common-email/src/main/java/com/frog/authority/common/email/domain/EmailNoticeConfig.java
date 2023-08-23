package com.frog.authority.common.email.domain;

import com.frog.authority.common.email.enums.NoticeType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

/**
 * @author frog
 */
@Data
@TableName("t_email_notice_config")
@EqualsAndHashCode(callSuper = false)
public class EmailNoticeConfig extends BaseEntity {

    private static final long serialVersionUID = -9115916349689215283L;

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 通知类型
     */
    @TableField("notice_type")
    private NoticeType noticeType;

    /**
     * 邮件主题
     */
    @TableField("subject")
    private String subject;

    /**
     * 发件人
     */
    @TableField("from")
    private String from;

    /**
     * 邮件收件人，多个以英文逗号隔开
     */
    @TableField("to")
    private String to;

    /**
     * 邮件抄送人，多个以英文逗号隔开
     */
    @TableField("cc")
    private String cc;

    public boolean isEmpty() {
        return StringUtils.isEmpty(subject) || StringUtils.isEmpty(from) || StringUtils.isEmpty(to);
    }
}
