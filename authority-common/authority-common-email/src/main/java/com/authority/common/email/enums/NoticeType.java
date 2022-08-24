package com.authority.common.email.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.frog.authority.common.core.enums.ExceptionType;
import com.frog.authority.common.core.exception.ExceptionFactory;
import lombok.Getter;

/**
 * 通知类型枚举类
 * @author frog
 */
@Getter
public enum NoticeType {

    ;

    @EnumValue
    private final String code;

    private final String desc;

    NoticeType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static NoticeType findByCode(String code) {
        for (NoticeType noticeType: NoticeType.values()) {
            if (noticeType.matches(code)) {
                return noticeType;
            }
        }
        throw ExceptionFactory.ex(ExceptionType.ILLEGAL_ARGUMENT, "通知类型[%s]格式错误", code);
    }

    public boolean matches(String code) {
        return this.code.equals(code);
    }
}
