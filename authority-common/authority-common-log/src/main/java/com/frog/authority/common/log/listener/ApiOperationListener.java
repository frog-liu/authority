package com.frog.authority.common.log.listener;

import com.frog.authority.common.log.domain.OperationDetailLog;
import com.frog.authority.common.log.domain.OperationLog;
import com.frog.authority.common.log.model.RequestInfo;
import com.frog.authority.common.log.service.IOperationDetailLogService;
import com.frog.authority.common.log.service.IOperationLogService;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liuhuan
 */
@Component
public class ApiOperationListener implements ApplicationListener<ApplicationEvent> {

    @Resource
    private IOperationLogService operationLogService;

    @Resource
    private IOperationDetailLogService operationDetailLogService;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        RequestInfo requestInfo = (RequestInfo) applicationEvent.getSource();
        // todo 区分成功失败、createBy被覆盖问题
        OperationLog operationLog = new OperationLog();
        operationLog.setSummary(requestInfo.getSummary());
        operationLog.setUrl(requestInfo.getUrl());
        operationLog.setMethod(requestInfo.getMethod());
        operationLog.setLatency(requestInfo.getLatency());
        operationLogService.save(operationLog);
        OperationDetailLog operationDetailLog = new OperationDetailLog();
        operationDetailLog.setHeaderId(operationLog.getId());
        operationDetailLog.setParams(requestInfo.getParams());
        operationDetailLog.setResult(requestInfo.getResult());
        operationDetailLogService.save(operationDetailLog);
    }
}
