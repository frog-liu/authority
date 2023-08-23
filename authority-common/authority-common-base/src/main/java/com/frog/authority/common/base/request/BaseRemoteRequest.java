package com.frog.authority.common.base.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 对外开放接口，请求参数基类，保证接口幂等性，每个请求全局唯一请求id
 *
 * @author frog
 */
@Data
public class BaseRemoteRequest implements Serializable {

    private static final long serialVersionUID = 5830127583417315749L;

    @NotNull(message = "请求id不能为空!")
    @ApiModelProperty(value = "请求id", required = true)
    private Long requestId;

}
