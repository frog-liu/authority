package com.frog.authority.common.base.exception;

/**
 * 重复请求异常
 *
 * @author frog
 */
public class DuplicateRequestException extends RuntimeException {

    private static final long serialVersionUID = -1L;

    public DuplicateRequestException(String message) {
        super(message);
    }

    public DuplicateRequestException(Throwable throwable) {
        super(throwable);
    }

    public DuplicateRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
