package com.frog.authority.common.base.controller;

import com.frog.authority.common.base.enums.StatusEnum;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;

/**
 * @author liuhuan
 */
public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        handlerEnum(binder);
    }

    private void handlerEnum(WebDataBinder binder) {
        binder.registerCustomEditor(StatusEnum.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(StringUtils.isEmpty(text) ? null : StatusEnum.findByCode(Integer.valueOf(text)));
            }
        });
    }
}
