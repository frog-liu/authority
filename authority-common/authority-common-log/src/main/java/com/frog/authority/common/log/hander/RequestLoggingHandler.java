package com.frog.authority.common.log.hander;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONValidator;
import com.frog.authority.common.base.util.RequestUtils;
import com.frog.authority.common.log.event.ApiOperationEvent;
import com.frog.authority.common.log.model.RequestInfo;
import com.frog.authority.security.util.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author liuhuan
 */
@Slf4j
@Order(2)
@Component
public class RequestLoggingHandler implements ApiOperationHandler {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Object handle(ProceedingJoinPoint joinPoint, ApiOperation apiOperation) throws Throwable {
        String requestUrl = RequestUtils.getRequestUrl();
        Object result;
        // GET请求不做处理
        if (!HttpMethod.GET.matches(apiOperation.httpMethod())) {
            long startTime = System.currentTimeMillis();
            String argsJson = getArgsJson(joinPoint, apiOperation.httpMethod());
            RequestInfo requestInfo = new RequestInfo();
            requestInfo.setSummary(apiOperation.value());
            requestInfo.setUrl(requestUrl);
            requestInfo.setMethod(apiOperation.httpMethod());
            requestInfo.setParams(argsJson);
            String resultJson = "";
            try {
                result = joinPoint.proceed();
                JSONValidator jsonValidator = JSONValidator.from(String.valueOf(result));
                if (jsonValidator.validate()) {
                    resultJson = JSON.toJSONString(result);
                }
            } catch (Exception e) {
                resultJson = e.getMessage();
                throw e;
            } finally {
                requestInfo.setResult(resultJson);
                requestInfo.setLatency(System.currentTimeMillis() - startTime);
                requestInfo.setUserId(SecurityUtils.getCurrentUserId());
                applicationEventPublisher.publishEvent(new ApiOperationEvent(requestInfo));
            }
        } else {
            result = joinPoint.proceed();
        }
        return result;
    }

}
