package com.frog.authority.common.log.hander;

import com.frog.authority.common.base.util.RequestUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author liuhuan
 */
@Slf4j
@Order(2)
@Component
public class RequestLoggingHandler implements ApiOperationHandler {

    @Override
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        String httpMethod = RequestUtils.getRequest().getMethod();
        String requestUrl = RequestUtils.getRequest().getRequestURI();
        Object result;
        // GET请求不做处理
        if (!HttpMethod.GET.matches(httpMethod)) {
            ApiOperation apiOperation = getApiOperation(joinPoint);
            try {
                long startTime = System.currentTimeMillis();
                result = joinPoint.proceed();
                String argsJson = getArgsJson(joinPoint, httpMethod);
                log.info("耗时:{}ms,请求地址:{},请求参数:{}", System.currentTimeMillis() - startTime, requestUrl, argsJson);
            } catch (Exception e) {
                throw e;
            }
        } else {
            result = joinPoint.proceed();
        }
        return result;
    }

}
