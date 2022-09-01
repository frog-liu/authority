package com.frog.authority.admin.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author frog
 */
@Data
@ApiModel("select_option")
public class OptionVO<T> implements Serializable {

    private static final long serialVersionUID = 3989908470401103035L;

    @ApiModelProperty("选项的值")
    private T value;

    @ApiModelProperty("选项的标签")
    private String label;
}
