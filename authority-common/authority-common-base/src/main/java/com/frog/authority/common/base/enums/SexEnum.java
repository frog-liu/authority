package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.frog.authority.common.base.exception.ExceptionFactory;
import lombok.Getter;

/**
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
     * 未知
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
        throw ExceptionFactory.ex(ExceptionType.ILLEGAL_ARGUMENT, "性别格式不正确:%s", code);
    }
}
