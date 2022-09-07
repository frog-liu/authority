package com.frog.authority.common.base.exception;

import com.frog.authority.common.base.enums.ExceptionType;

/**
 * 异常工厂类
 * @author frog
 */
public class ExceptionFactory {

    public static RuntimeException ex(ExceptionType type, String message, Object... args) {
        message = String.format(message, args);
        switch (type) {
            case ILLEGAL_ARGUMENT:
                return new IllegalArgumentException(message);
            case BUSINESS:
                return new BusinessException(message);
            default:
                return new RuntimeException(message);
        }
    }
}
