package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单 实体类
 * @author frog
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 3858582236916867598L;

    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父类菜单id
     */
    @TableField
    private Long parentId;

    /**
     * 菜单名称
     */
    @TableField
    private String name;

    /**
     * 菜单路径
     */
    @TableField
    private String path;

    /**
     * 菜单图标
     */
    @TableField
    private String icon;

    /**
     * 权限
     */
    @TableField
    private String permission;

}
