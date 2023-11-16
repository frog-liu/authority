package com.frog.authority.common.log.event;

import com.frog.authority.common.log.model.RequestInfo;
import org.springframework.context.ApplicationEvent;

/**
 * 基于@ApiOperation注解日志记录时间
 *
 * @author liuhuan
 */
public class ApiOperationEvent extends ApplicationEvent {

    public ApiOperationEvent(RequestInfo requestInfo) {
        super(requestInfo);
    }
}
