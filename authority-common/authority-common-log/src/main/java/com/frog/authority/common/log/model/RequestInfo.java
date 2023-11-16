package com.frog.authority.common.log.model;

import lombok.Data;

/**
 * 请求信息
 *
 * @author liuhuan
 */
@Data
public class RequestInfo {

    /**
     * 概述
     */
    private String summary;

    /**
     * 接口url
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求参数,json格式
     */
    private String params;

    /**
     * 返回结果,json格式
     */
    private String result;

    /**
     * 请求耗时
     */
    private Long latency;

    /**
     * 操作人id
     */
    private Long userId;

}
