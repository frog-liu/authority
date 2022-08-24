package com.frog.authority.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.frog.authority.common.core.exception.ExceptionFactory;
import lombok.Getter;

/**
 * @author frog
 */
@Getter
public enum StatusEnum {

    /**
     * 无效
     */
    INVALID(0),

    /**
     * 有效
     */
    VALID(1),

    /**
     * 锁定
     */
    LOCKED(2),

    ;

    @EnumValue
    private Integer code;

    StatusEnum(Integer code) {
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
        throw ExceptionFactory.ex(ExceptionType.ILLEGAL_ARGUMENT, "状态格式不正确:%s", code);
    }
}
