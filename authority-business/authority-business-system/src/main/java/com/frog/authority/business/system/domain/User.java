package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.authority.common.core.enums.SexEnum;
import com.frog.authority.common.core.enums.StatusEnum;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户 实体类
 * @author frog
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    private static final long serialVersionUID = 4564080599152242116L;

    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号
     */
    @TableField
    private String username;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 用户密码
     */
    @TableField
    private String name;

    /**
     * 性别
     */
    @TableField
    private SexEnum sex;

    /**
     * 手机号
     */
    @TableField
    private String phone;

    /**
     * 邮箱
     */
    @TableField
    private String email;

    /**
     * 头像
     */
    @TableField
    private String avatar;

    /**
     * 状态
     */
    @TableField("status")
    private StatusEnum status;

    /**
     * 角色列表
     */
    @TableField(exist = false)
    private List<String> roleList;

}
