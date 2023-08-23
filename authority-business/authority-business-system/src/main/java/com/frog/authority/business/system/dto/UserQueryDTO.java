package com.frog.authority.business.system.dto;

import com.frog.authority.common.base.enums.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户列表查询DTO
 * @author liuhuan
 */
@Data
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = -2585710421696222972L;

    @ApiModelProperty("部门id列表")
    private Long deptId;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态")
    private StatusEnum status;

    @ApiModelProperty("创建时间-开始")
    private LocalDateTime beginTime;

    @ApiModelProperty("创建时间-结束")
    private LocalDateTime endTime;

}
