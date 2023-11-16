package com.frog.authority.common.base.factory;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

/**
 * 异常工厂类
 *
 * @author frog
 */
@Slf4j
public class ExceptionFactory {

    /**
     * 按照异常类型创建异常
     *
     * @param clazz 异常类型
     * @param message 异常信息
     * @param args 异常参数
     * @return 异常
     */
    public static RuntimeException ex(Class<? extends RuntimeException> clazz, String message, Object... args) {
        message = String.format(message, args);
        try {
            Constructor<? extends RuntimeException> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(message);
        } catch (Exception e) {
            log.error("ExceptionFactory ex NoSuchMethodException", e);
        }
        return new RuntimeException(message);
    }
}
