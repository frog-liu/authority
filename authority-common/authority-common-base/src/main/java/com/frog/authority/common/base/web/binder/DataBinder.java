package com.frog.authority.common.base.web.binder;

import org.springframework.web.bind.WebDataBinder;

/**
 * web data binder
 *
 * @author liuhuan
 */
public interface DataBinder {

    /**
     * 注册binder
     *
     * @param binder binder
     */
    void register(WebDataBinder binder);
}
