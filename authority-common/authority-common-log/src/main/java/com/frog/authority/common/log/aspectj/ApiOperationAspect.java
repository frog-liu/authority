package com.frog.authority.common.log.aspectj;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.frog.authority.common.base.request.BaseRemoteRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.TimerTask;

/**
 * 该类是一个切面，用于处理接口的重复请求、请求参数和返回结果的记录等
 * @author frog
 */
@Slf4j
@Aspect
@Order(100)
@Component
public class ApiOperationAspect {

    /**
     * 定义 Redis 布隆过滤器的名称
     */
    private static final String BLOOM_FILTER_NAME = "scm-request-bloom-filter";

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

    /**
     * 定义切入点:@ApiOperation注解
     */
    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void apiOperationPointcut() {}

    /**
     * 定义环绕通知，处理请求的重复提交检测、请求的处理结果和异常的处理逻辑
     * @param joinPoint 切入点
     * @return 返回值
     * @throws Throwable 处理异常
     */
    @Around("apiOperationPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 查找 @ApiOperation 注解
        ApiOperation apiOperation = this.findApiOperation(joinPoint);
        // 计算请求耗时
        long startTime = System.currentTimeMillis();
        // 过滤重复请求
        this.filterRepeatRequest(joinPoint.getArgs());
        Object result;
        try {
            // 执行目标方法
            result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            // 处理返回结果
            this.handle(joinPoint, apiOperation, endTime - startTime, result, null);
        } catch (Exception ex) {
            // 处理异常
            long endTime = System.currentTimeMillis();
            this.handle(joinPoint, apiOperation, endTime - startTime, null, ex);
            if (ex instanceof IllegalArgumentException || ex instanceof BusinessException) {
                throw new OperationException(apiOperation.value() + ",操作失败:" + ex.getMessage());
            } else {
                throw new OperationException(apiOperation.value() + "操作失败,请联系管理人员!");
            }
        }
        return result;
    }

    private ApiOperation findApiOperation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ApiOperation apiOperation = AnnotationUtils.findAnnotation(signature.getMethod(), ApiOperation.class);
        if (Objects.nonNull(apiOperation)) {
            return apiOperation;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), ApiOperation.class);
    }

    /**
     * 过滤重复请求
     * @param args 目标方法请求参数
     */
    private void filterRepeatRequest(Object[] args) {
        BaseRemoteRequest remoteRequest = this.findRemoteRequest(args);
        // 根据requestId重复请求校验
        if (remoteRequest != null) {
            // 判断请求id是否已经存在于布隆过滤器中
            if (bloomFilter.contains(remoteRequest.getRequestId())) {
                throw ExceptionFactory.ex(ExceptionType.BUSINESS, "业务系统请求id重复: %s", remoteRequest.getRequestId());
            }
            // 将请求id添加到布隆过滤器中
            bloomFilter.add(remoteRequest.getRequestId());
        }
    }

    /**
     * 查找RemoteRequest类型的参数
     * @param args 目标方法请求参数
     * @return 如果有则返回RemoteRequest类型的参数，否则返回null
     */
    private BaseRemoteRequest findRemoteRequest(Object[] args) {
        if (ArrayUtils.isNotEmpty(args)) {
            for (Object arg: args) {
                if (arg instanceof BaseRemoteRequest) {
                    return (BaseRemoteRequest) arg;
                }
            }
        }
        return null;
    }

    private void handle(JoinPoint joinPoint, ApiOperation apiOperation, long latency, Object result, Exception ex) {
        // 获取请求方法类型 & 请求参数
        String httpMethod = RequestUtils.getMethod();
        String argsJson = getArgsJson(httpMethod, joinPoint);
        // 非GET请求则记录日志
        if (!HttpMethod.GET.matches(httpMethod)) {
            if (ex != null) {
                RequestInfo requestInfo = new RequestInfo(argsJson, ex.getMessage(), latency);
                TimerTask timerTask = LogThreadFactory.newTimerTask(apiOperation, requestInfo, StatusEnum.INVALID);
                AsyncManager.execute(timerTask);
                // IllegalArgumentException和BusinessException打印info日志, 通知操作人员处理
                if (ex instanceof IllegalArgumentException || ex instanceof BusinessException) {
                    log.info(JSON.toJSONString(requestInfo));
                } else {
                    // 其他异常类型打印error日志,通知开发处理
                    log.error("{}{}异常:{}", ThreadLocalUtils.getUsername(), apiOperation.value(), ex.getMessage(), ex);
                }
            } else {
                // 没有异常, 记录请求日志和返回结果
                RequestInfo requestInfo = new RequestInfo(argsJson, JSONUtil.toJsonStr(result), latency);
                TimerTask timerTask = LogThreadFactory.newTimerTask(apiOperation, requestInfo, StatusEnum.VALID);
                AsyncManager.execute(timerTask);
                log.info(JSON.toJSONString(requestInfo));
            }
        }
    }

    /**
     * 获取请求参数并转为JSON字符串
     * @param httpMethod HTTP请求方法
     * @param joinPoint 连接点对象
     * @return 转换后的JSON字符串
     */
    private String getArgsJson(String httpMethod, JoinPoint joinPoint) {
        String argsJson = "";
        if (!HttpMethod.GET.matches(httpMethod)) {
            if (HttpMethod.PUT.matches(httpMethod) || HttpMethod.POST.matches(httpMethod)) {
                argsJson = this.toJsonString(joinPoint.getArgs());
            } else {
                Map<?, ?> argsMap = (Map<?, ?>) RequestUtils.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
                if (!MapUtil.isEmpty(argsMap)) {
                    argsJson = argsMap.toString();
                } else {
                    argsJson = this.toJsonString(joinPoint.getArgs());
                }
            }
        }
        return argsJson;
    }

    /**
     * 将参数数组转换成JSON字符串
     * @param args 参数数组
     * @return 转换后的JSON字符串
     */
    private String toJsonString(Object[] args) {
        StringBuilder jsonBuilder = new StringBuilder();
        if (!ArrayUtils.isEmpty(args)) {
            for (Object arg: args) {
                // 对于需要过滤的参数，不加入JSON字符串中
                if (!isFilter(arg)) {
                    jsonBuilder.append(JSON.toJSONString(arg)).append(" ");
                }
            }
        }
        return jsonBuilder.toString().trim();
    }

    /**
     * 判断参数是否需要过滤
     * @param arg 参数对象
     * @return 需要过滤返回true, 否则返回false
     */
    @SuppressWarnings("rawtypes")
    private boolean isFilter(Object arg) {
        if (arg == null) {
            return true;
        }
        Class<?> cls = arg.getClass();
        // 如果是数组、集合&map类型，则获取里面元素，如果元素为MultipartFile则返回true，否则返回false
        if (cls.isArray()) {
            return cls.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(cls)) {
            Collection collection = (Collection) arg;
            for (Object value: collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(cls)) {
            Map map = (Map) arg;
            for (Object value: map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        // 如果参数为MultipartFile、HttpServletRequest、HttpServletResponse或BindingResult类型，则不转成json格式
        return arg instanceof MultipartFile || arg instanceof HttpServletRequest || arg instanceof HttpServletResponse
                || arg instanceof BindingResult;
    }

}
