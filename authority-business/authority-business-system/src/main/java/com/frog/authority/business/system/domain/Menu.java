package com.frog.authority.business.system.domain;

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
    @TableId
    private Long id;

    /**
     * 父类菜单id
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 权限
     */
    private String permission;

}
