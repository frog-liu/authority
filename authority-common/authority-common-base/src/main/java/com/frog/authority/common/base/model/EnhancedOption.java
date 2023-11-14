package com.frog.authority.common.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * html <option>增强标签</option>
 *
 * @author liuhuan
 */
@Data
@NoArgsConstructor
@ApiModel("<option>增强标签")
public class EnhancedOption<T, D> extends Option<T> {

    private static final long serialVersionUID = -1L;

    public EnhancedOption(T value, String label, D data) {
        super(value, label);
        this.data = data;
    }

    @ApiModelProperty("附件data")
    private D data;

}