package com.frog.authority.common.base.util;


import org.reflections.Reflections;

import java.util.Set;

/**
 * 反射工具类
 *
 * @author liuhuan
 */
public class ReflectUtils {

    private static final String defaultPackageName = "com.frog.authority";

    /**
     * 获取所有子类
     *
     * @param type 指定类型
     * @param <T> 泛型
     * @return 所有子类class
     */
    public static <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        Reflections reflection = new Reflections(defaultPackageName);
        return reflection.getSubTypesOf(type);
    }
}
