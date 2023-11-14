package com.frog.authority.common.base.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * 性别枚举类
 *
 * @author frog
 */
@Getter
public enum GenderEnum implements IBaseEnum<Integer> {

    /**
     * 女性
     */
    FEMALE(0, "女"),

    /**
     * 男性
     */
    MALE(1, "男"),

    /**
     * 保密
     */
    SECRECY(-1, "保密");

    @EnumValue
    private final Integer value;

    private final String label;

    GenderEnum(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

}
