package com.frog.authority.common.base.enums;

import java.util.EnumSet;

/**
 * 枚举基类
 *
 * @author frog
 */
public interface IBaseEnum<T> {

    /**
     * 获取枚举值
     *
     * @return 返回value
     */
    T getValue();

    /**
     * 枚举值是否匹配
     *
     * @param value 枚举值
     * @return 如果匹配则返回true，否则返回false
     */
    default boolean matches(T value) {
        return this.getValue().equals(value) || this.getValue().toString().equals(String.valueOf(value));
    }

    /**
     * 根据值获取枚举
     *
     * @param value 枚举值
     * @param clazz 枚举类
     * @param <E> 枚举
     * @return 枚举
     */
    static <E extends Enum<E> & IBaseEnum> E getByValue(Object value, Class clazz) {
        EnumSet<E> allEnums = EnumSet.allOf(clazz);
        E matchesEnum = allEnums.stream()
                .filter(e -> e.matches(value))
                .findFirst()
                .orElse(null);
        return matchesEnum;
    }
}
