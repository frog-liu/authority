package com.frog.authority.common.log.hander;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.frog.authority.common.base.util.RequestUtils;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * ApiOperation处理器接口
 *
 * @author frog
 */
public interface ApiOperationHandler {

    /**
     * 处理joinPoint
     *
     * @param joinPoint 切入点
     * @return 执行结果
     * @throws Throwable 异常
     */
    Object handle(ProceedingJoinPoint joinPoint) throws Throwable;

    /**
     * 获取ApiOperation注解
     *
     * @param joinPoint 切入点
     * @return ApiOperation对象
     */
    default ApiOperation getApiOperation(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ApiOperation apiOperation = AnnotationUtils.findAnnotation(signature.getMethod(), ApiOperation.class);
        if (Objects.nonNull(apiOperation)) {
            return apiOperation;
        }
        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), ApiOperation.class);
    }

    /**
     * 获取请求参数并转为JSON字符串
     *
     * @param httpMethod HTTP请求方法
     * @param joinPoint 连接点对象
     * @return 转换后的JSON字符串
     */
    default String getArgsJson(ProceedingJoinPoint joinPoint, String httpMethod) {
        String argsJson;
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
        return argsJson;
    }

    /**
     * 将参数数组转换成JSON字符串
     *
     * @param args 参数数组
     * @return 转换后的JSON字符串
     */
    default String toJsonString(Object[] args) {
        StringBuilder jsonBuilder = new StringBuilder();
        if (!ArrayUtils.isEmpty(args)) {
            for (Object arg: args) {
                // 对于需要过滤的参数，不加入JSON字符串中
                if (!isFilter(arg)) {
                    jsonBuilder.append(args.toString()).append(" ");
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
    default boolean isFilter(Object arg) {
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
