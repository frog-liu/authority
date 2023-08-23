package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 性别枚举类
 *
 * @author frog
 */
@Getter
public enum SexEnum {

    /**
     * 女性
     */
    FEMALE(0),

    /**
     * 男性
     */
    MALE(1),

    /**
     * 保密
     */
    UN_KNOW(2),

    ;

    @EnumValue
    private final Integer code;

    SexEnum(Integer code) {
        this.code = code;
    }

    public boolean matches(Integer code) {
        return this.code.equals(code);
    }

    public static StatusEnum findByCode(Integer code) {
        for (StatusEnum status: StatusEnum.values()) {
            if (status.matches(code)) {
                return status;
            }
        }
        return null;
    }
}
