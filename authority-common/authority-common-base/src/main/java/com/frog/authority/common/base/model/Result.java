package com.frog.authority.common.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import java.io.Serializable;

/**
 * @author frog
 */
@Getter
@ToString
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -1675318165343472310L;

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";

    @ApiModelProperty("返回code")
    private final int code;

    @ApiModelProperty("返回消息")
    private final String msg;

    @ApiModelProperty("返回body")
    private final T body;

    private Result(Integer code, String msg, T body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public static<T> Result<T> ok(String message) {
        return ok(message, (T)message);
    }

    public static<T> Result<T> ok(T body) {
        return ok(SUCCESS, body);
    }

    public static<T> Result<T> ok(String message, T body) {
        return new Result<>(HttpStatus.OK.value(), message, body);
    }

    public static<T> Result<T> fail(String message) {
        return fail(message, null);
    }

    public static<T> Result<T> fail(T body) {
        return fail(FAIL, body);
    }

    public static<T> Result<T> fail(String message, T body) {
        return fail(HttpStatus.INTERNAL_SERVER_ERROR, message, body);
    }

    public static<T> Result<T> fail(HttpStatus httpStatus, String message, T body) {
        return fail(httpStatus.value(), message, body);
    }

    private static<T> Result<T> fail(int code, String message, T body) {
        return new Result<>(code, message, body);
    }

    public static boolean isSuccess(Result<?> result) {
        return result != null && HttpStatus.OK.value() == result.getCode();
    }
}
