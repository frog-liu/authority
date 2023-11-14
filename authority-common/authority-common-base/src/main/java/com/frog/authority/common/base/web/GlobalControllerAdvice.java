package com.frog.authority.common.base.web;

import com.frog.authority.common.base.web.binder.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author liuhuan
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    private final List<DataBinder> dataBinderList;

    public GlobalControllerAdvice(List<DataBinder> dataBinderList) {
        this.dataBinderList = dataBinderList;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        for (DataBinder dataBinder : dataBinderList) {
            dataBinder.register(binder);
        }
    }
}
