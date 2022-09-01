package com.frog.authority.security.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author frog
 */
public interface ICaptchaService {

    /**
     * 创建验证码
     * @param request 请求
     * @param response 返回
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 校验验证码
     * @param request 请求
     * @param response 返回
     */
    void check(HttpServletRequest request, HttpServletResponse response);
}
