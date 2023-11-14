package com.frog.authority.common.log.hander;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ApiOperation处理器链
 *
 * @author liuhuan
 */
@Component
public class ApiOperationHandlerChain {

    private final List<ApiOperationHandler> handlers;

    @Autowired
    public ApiOperationHandlerChain(List<ApiOperationHandler> handlers) {
        this.handlers = handlers;
    }

    public Object handle(ProceedingJoinPoint joinPoint, ApiOperation apiOperation) throws Throwable {
        Object result = null;
        for (ApiOperationHandler handler : handlers) {
            result = handler.handle(joinPoint, apiOperation);
            if (result != null) {
                break;
            }
        }
        return result;
    }

}
