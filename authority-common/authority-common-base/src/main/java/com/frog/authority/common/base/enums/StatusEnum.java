package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 状态枚举类
 *
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

    ;

    @EnumValue
    private final Integer code;

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
        return null;
    }
}
