package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.authority.common.base.enums.GenderEnum;
import com.frog.authority.common.base.enums.ValidityStatus;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    @TableId
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 性别
     */
    private GenderEnum sex;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 状态
     */
    private ValidityStatus status;

}
