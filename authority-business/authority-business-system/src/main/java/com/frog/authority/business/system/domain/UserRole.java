package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;

/**
 * @author frog
 */
@Data
@TableName("t_user_role")
public class UserRole extends BaseEntity {

    /**
     * 用户id
     */
    @TableField("user_Id")
    private Long userId;

    /**
     * 角色id
     */
    @TableField("role_Id")
    private Long roleId;
}
