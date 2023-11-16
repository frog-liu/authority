package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 成功状态枚举类
 *
 * @author frog
 */
@Getter
public enum StatusEnum implements IBaseEnum<Integer> {

    /**
     * 失败
     */
    FAIL(0, "失败"),

    /**
     * 失败
     */
    SUCCESS(1, "失败");

    @EnumValue
    private final Integer value;

    private final String label;

    StatusEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}
