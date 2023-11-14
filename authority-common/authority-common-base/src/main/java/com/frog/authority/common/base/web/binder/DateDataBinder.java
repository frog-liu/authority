package com.frog.authority.common.base.web.binder;

import com.frog.authority.common.base.util.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

/**
 * 日期类格式转换
 *
 * @author liuhuan
 */
@Component
public class DateDataBinder implements DataBinder {

    @Override
    public void register(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(DateUtils.toLocalDateTime(text));
            }
        });
    }
}
