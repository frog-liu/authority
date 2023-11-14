package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 有效无效状态枚举类
 *
 * @author frog
 */
@Getter
public enum ValidityStatus implements IBaseEnum<Integer> {

    /**
     * 无效
     */
    INVALID(0, "无效"),

    /**
     * 有效
     */
    VALID(1, "有效");

    @EnumValue
    private final Integer value;

    private final String label;

    ValidityStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}
