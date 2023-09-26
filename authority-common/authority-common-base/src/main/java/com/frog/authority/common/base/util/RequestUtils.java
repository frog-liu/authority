package com.frog.authority.common.base.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 *
 * @author frog
 */
public class RequestUtils {

    /**
     * 获取Integer类型请求参数
     *
     * @param param  参数名
     * @return 参数值
     */
    public static Integer getIntegerParam(String param) {
        String value = getRequest().getParameter(param);
        if (!StringUtils.isEmpty(value)) {
            try {
                return Integer.valueOf(value);
            } catch (Exception e) {
                // ignore exception
            }
        }
        return null;
    }

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
