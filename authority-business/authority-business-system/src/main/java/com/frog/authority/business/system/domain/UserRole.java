package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frog
 */
@Data
@TableName("t_user_role")
@EqualsAndHashCode(callSuper = false)
public class UserRole extends BaseEntity {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}
