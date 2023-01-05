package com.frog.authority.common.base.util;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author frog
 */
public class RequestUtils {

    public static Integer getInteger(String param) {
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

    private static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
