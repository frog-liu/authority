package com.frog.authority.common.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * html <option>增强标签</option>
 *
 * @author liuhuan
 */
@Data
@NoArgsConstructor
@ApiModel("<option>增强标签")
@EqualsAndHashCode(callSuper = true)
public class EnhancedOption<T, M> extends Option<T> {

    public EnhancedOption(T value, String label, M data) {
        super(value, label);
        this.data = data;
    }

    @ApiModelProperty("附件data")
    private M data;

}