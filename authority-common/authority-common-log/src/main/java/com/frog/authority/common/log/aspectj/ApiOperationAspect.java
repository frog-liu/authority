package com.frog.authority.common.log.aspectj;

import com.frog.authority.common.log.hander.ApiOperationHandlerChain;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 该类是一个切面，用于处理接口的重复请求、请求参数和返回结果的记录等
 *
 * @author frog
 */
@Slf4j
@Aspect
@Order(100)
@Component
public class ApiOperationAspect {

    @Resource
    private ApiOperationHandlerChain apiOperationHandlerChain;

    /**
     * 定义切入点:@ApiOperation注解
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void apiOperationPointcut() {
    }

    /**
     * 定义环绕通知，处理请求的重复提交检测、请求的处理结果和异常的处理逻辑
     *
     * @param joinPoint 切入点
     * @return 返回值
     * @throws Throwable 处理异常
     */
    @Around("apiOperationPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        return apiOperationHandlerChain.handle(joinPoint);
    }

}
