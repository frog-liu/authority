package com.frog.authority.common.log.hander;

import com.frog.authority.common.base.exception.DuplicateRequestException;
import com.frog.authority.common.base.factory.ExceptionFactory;
import com.frog.authority.common.base.request.BaseRemoteRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 重复请求处理器-基于布隆过滤器
 *
 * @author frog
 */
@Slf4j
@Order(1)
@Component
public class DuplicateRequestHandler implements ApiOperationHandler {

    /**
     * 定义 Redis 布隆过滤器的名称
     */
    private static final String BLOOM_FILTER_NAME = "BF:Remote-Req";

    @Resource
    private RedissonClient redissonClient;

    private RBloomFilter<Long> bloomFilter;

    /**
     * 在初始化之后执行,获取布隆过滤器实例,尝试初始化布隆过滤器，设置最大元素数和误差率
     */
    @PostConstruct
    public void init() {
        this.bloomFilter = this.redissonClient.getBloomFilter(BLOOM_FILTER_NAME);
        this.bloomFilter.tryInit(200000000L, 0.001);
    }

    @Override
    public Object handle(ProceedingJoinPoint joinPoint, ApiOperation apiOperation) {
        BaseRemoteRequest baseRemoteRequest = getBaseRemoteRequest(joinPoint);
        if (baseRemoteRequest != null) {
            Long requestId = baseRemoteRequest.getRequestId();
            if (bloomFilter.contains(requestId)) {
                log.error("请求重复(requestId={}):{}", baseRemoteRequest.getRequestId(), baseRemoteRequest);
                throw ExceptionFactory.ex(DuplicateRequestException.class, "请求重复(requestId=%s)", baseRemoteRequest.getRequestId());
            }
        }
        return null;
    }

    private BaseRemoteRequest getBaseRemoteRequest(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof BaseRemoteRequest) {
                    return (BaseRemoteRequest) arg;
                }
            }
        }
        return null;
    }
}
