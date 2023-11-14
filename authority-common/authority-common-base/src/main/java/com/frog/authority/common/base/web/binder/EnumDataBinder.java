package com.frog.authority.common.base.web.binder;

import com.frog.authority.common.base.enums.IBaseEnum;
import com.frog.authority.common.base.util.ReflectUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;

import java.beans.PropertyEditorSupport;
import java.util.Set;

/**
 * 枚举类转换
 *
 * @author liuhuan
 */
@Component
public class EnumDataBinder implements DataBinder {

    @Override
    public void register(WebDataBinder binder) {
        Set<Class<? extends IBaseEnum>> enumClassSet = ReflectUtils.getSubTypesOf(IBaseEnum.class);
        for (Class<? extends IBaseEnum> enumClass : enumClassSet) {
            binder.registerCustomEditor(enumClass, new PropertyEditorSupport() {
                @Override
                public void setAsText(String text) throws IllegalArgumentException {
                    setValue(StringUtils.isEmpty(text) ? null : IBaseEnum.getByValue(text, enumClass));
                }
            });
        }
    }
}
