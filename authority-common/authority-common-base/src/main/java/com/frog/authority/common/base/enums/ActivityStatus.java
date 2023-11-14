package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 正常、停用状态枚举类
 *
 * @author liuhuan
 */
@Getter
public enum ActivityStatus implements IBaseEnum<Integer> {

    /**
     * 正常
     */
    ACTIVE(1, "正常"),

    /**
     * 停用
     */
    INACTIVE(0, "停用");

    @EnumValue
    private final Integer value;

    private final String label;

    ActivityStatus(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
}
