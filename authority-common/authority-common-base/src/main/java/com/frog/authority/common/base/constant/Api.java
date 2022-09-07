package com.frog.authority.common.base.constant;

/**
 * api常量
 * @author frog
 */
public class Api {

    /**
     * security模块
     */
    public interface Security {

        /**
         * 所有授权路径
         */
        String OAUTH_ALL = "/oauth/**";

        /**
         * 获取token
         */
        String OAUTH_TOKEN = "/oauth/token";
    }
}
