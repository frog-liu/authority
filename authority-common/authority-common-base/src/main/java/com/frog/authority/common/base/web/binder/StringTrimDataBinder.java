package com.frog.authority.common.base.web.binder;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

/**
 * 字符串trim
 *
 * @author liuhuan
 */
@Component
public class StringTrimDataBinder implements DataBinder {

    @Override
    public void register(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
