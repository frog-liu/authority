package com.frog.authority.business.system.vo;

import com.frog.authority.common.base.enums.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author liuhuan
 */
@Data
@ApiModel(value = "用户管理列表DTO")
public class UserManageVO implements Serializable {

    private static final long serialVersionUID = -1190749022890189969L;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态")
    private StatusEnum status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

}
