package com.frog.authority.common.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * html <option>标签</option>
 *
 * @author liuhuan
 */
@Data
@NoArgsConstructor
@ApiModel("<option>标签")
public class Option<T> implements Serializable {

    private static final long serialVersionUID = -1L;

    public Option(T value, String label) {
        this.value = value;
        this.label = label;
    }

    @ApiModelProperty("value")
    private T value;

    @ApiModelProperty("label")
    private String label;

}