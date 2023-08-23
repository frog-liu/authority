package com.frog.authority.common.email.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
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

    public boolean matches(String code) {
        return this.code.equals(code);
    }

    public static NoticeType findByCode(String code) {
        for (NoticeType noticeType: NoticeType.values()) {
            if (noticeType.matches(code)) {
                return noticeType;
            }
        }
        return null;
    }

}
