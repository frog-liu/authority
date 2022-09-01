package com.frog.authority.common.core.util;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.frog.authority.common.core.enums.ExceptionType;
import com.frog.authority.common.core.exception.ExceptionFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 自定义断言工具类
 * 可指定抛出异常 {@link ExceptionType}, 默认抛出业务类型异常 {@link ExceptionType#BUSINESS}
 * @author frog
 */
public final class Assert {

    private Assert() {}

    /**
     * 断言这个 boolean 为 true
     * <p>为 false 则抛出异常</p>
     * @param type 异常类型
     * @param expression boolean 值
     * @param message    消息
     */
    public static void isTrue(ExceptionType type, boolean expression, String message, Object... params) {
        if (!expression) {
            throw ExceptionFactory.ex(type, message, params);
        }
    }

    /**
     * 断言这个 boolean 为 false
     * <p>为 true 则抛出异常</p>
     * @param type 异常类型
     * @param expression boolean 值
     * @param message    消息
     */
    public static void isFalse(ExceptionType type, boolean expression, String message, Object... params) {
        isTrue(type, !expression, message, params);
    }

    /**
     * 断言这个 object 不为 null
     * <p>为 null 则抛异常</p>
     * @param object  对象
     * @param message 消息
     */
    public static void notNull(Object object, String message, Object... params) {
        notNull(ExceptionType.BUSINESS, object, message, params);
    }

    /**
     * 断言这个 object 不为 null
     * <p>为 null 则抛异常</p>
     * @param type 异常类型
     * @param object  对象
     * @param message 消息
     */
    public static void notNull(ExceptionType type, Object object, String message, Object... params) {
        isTrue(type, object != null, message, params);
    }

    /**
     * 断言这个 object 为 null
     * <p>不为 null 则抛异常</p>
     * @param object  对象
     * @param message 消息
     */
    public static void isNull(Object object, String message, Object... params) {
        isNull(ExceptionType.BUSINESS, object, message, params);
    }

    /**
     * 断言这个 object 为 null
     * <p>不为 null 则抛异常</p>
     * @param type 异常类型
     * @param object  对象
     * @param message 消息
     */
    public static void isNull(ExceptionType type, Object object, String message, Object... params) {
        isTrue(type, object == null, message, params);
    }

    /**
     * 断言这个 value 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param value   字符串
     * @param message 消息
     */
    public static void notEmpty(String value, String message, Object... params) {
        notEmpty(ExceptionType.BUSINESS, value, message, params);
    }

    /**
     * 断言这个 value 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param type 异常类型
     * @param value   字符串
     * @param message 消息
     */
    public static void notEmpty(ExceptionType type, String value, String message, Object... params) {
        isTrue(type, StringUtils.isNotBlank(value), message, params);
    }

    /**
     * 断言这个 collection 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param collection 集合
     * @param message    消息
     */
    public static void notEmpty(Collection<?> collection, String message, Object... params) {
        notEmpty(ExceptionType.BUSINESS, collection, message, params);
    }

    /**
     * 断言这个 collection 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param type 异常类型
     * @param collection 集合
     * @param message    消息
     */
    public static void notEmpty(ExceptionType type, Collection<?> collection, String message, Object... params) {
        isTrue(type, !CollectionUtils.isEmpty(collection), message, params);
    }

    /**
     * 断言这个 map 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param map     集合
     * @param message 消息
     */
    public static void notEmpty(Map<?, ?> map, String message, Object... params) {
        notEmpty(ExceptionType.BUSINESS, map, message, params);
    }

    /**
     * 断言这个 map 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param type 异常类型
     * @param map 集合
     * @param message 消息
     */
    public static void notEmpty(ExceptionType type, Map<?, ?> map, String message, Object... params) {
        isTrue(type, !CollectionUtils.isEmpty(map), message, params);
    }

    /**
     * 断言这个 数组 不为 empty
     * <p>为 empty 则抛异常</p>
     *
     * @param array   数组
     * @param message 消息
     */
    public static void notEmpty(Object[] array, String message, Object... params) {
        notEmpty(ExceptionType.BUSINESS, array, message, params);
    }

    /**
     * 断言这个 数组 不为 empty
     * <p>为 empty 则抛异常</p>
     * @param type 异常类型
     * @param array   数组
     * @param message 消息
     */
    public static void notEmpty(ExceptionType type, Object[] array, String message, Object... params) {
        isTrue(type, ArrayUtils.isNotEmpty(array), message, params);
    }
}
