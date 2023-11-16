package com.frog.authority.common.base.util;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.authority.common.base.factory.ExceptionFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义断言工具类
 *
 * @author frog
 */
public final class Assert {

    private Assert() {}

    /**
     * 断言这个 boolean 为 true
     * <p>为 false 则抛出异常</p>
     *
     * @param exClass 异常类型
     * @param expression 表达式真值
     * @param message    消息
     */
    public static void isTrue(Class<? extends RuntimeException> exClass, boolean expression, String message, Object... params) {
        if (!expression) {
            throw ExceptionFactory.ex(exClass, message, params);
        }
    }

    /**
     * 断言这个 boolean 为 false
     * <p>为 true 则抛出异常</p>
     *
     * @param exClass 异常类型
     * @param expression boolean 值
     * @param message    消息
     */
    public static void isFalse(Class<? extends RuntimeException> exClass, boolean expression, String message, Object... params) {
        isTrue(exClass, !expression, message, params);
    }

    /**
     * 断言这个 object 不为 null
     * <p>为 null 则抛异常</p>
     *
     * @param exClass 异常类型
     * @param object  对象
     * @param message 消息
     */
    public static void notNull(Class<? extends RuntimeException> exClass, Object object, String message, Object... params) {
        isTrue(exClass, object != null, message, params);
    }

    /**
     * 断言这个 object 为 null
     * <p>不为 null 则抛异常</p>
     *
     * @param exClass 异常类型
     * @param object  对象
     * @param message 消息
     */
    public static void isNull(Class<? extends RuntimeException> exClass, Object object, String message, Object... params) {
        isTrue(exClass, object == null, message, params);
    }

    /**
     * 断言这个 value 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param exClass 异常类型
     * @param value   字符串
     * @param message 消息
     */
    public static void notEmpty(Class<? extends RuntimeException> exClass, String value, String message, Object... params) {
        isTrue(exClass, StringUtils.isNotBlank(value), message, params);
    }

    /**
     * 断言这个 collection 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param exClass 异常类型
     * @param collection 集合
     * @param message    消息
     */
    public static void notEmpty(Class<? extends RuntimeException> exClass, Collection<?> collection, String message, Object... params) {
        isTrue(exClass, !CollectionUtils.isEmpty(collection), message, params);
    }

    /**
     * 断言这个 map 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param exClass 异常类型
     * @param map 集合
     * @param message 消息
     */
    public static void notEmpty(Class<? extends RuntimeException> exClass, Map<?, ?> map, String message, Object... params) {
        isTrue(exClass, !CollectionUtils.isEmpty(map), message, params);
    }

    /**
     * 断言这个 数组 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param exClass 异常类型
     * @param array   数组
     * @param message 消息
     */
    public static void notEmpty(Class<? extends RuntimeException> exClass, Object[] array, String message, Object... params) {
        isTrue(exClass, ArrayUtils.isNotEmpty(array), message, params);
    }
}
