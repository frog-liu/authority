package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门 实体类
 * @author frog
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Department extends BaseEntity {

    private static final long serialVersionUID = -3931103465637567392L;

    /**
     * 部门id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 上级部门id
     */
    @TableField
    private Long parentId;

    /**
     * 部门名称
     */
    @TableField
    private String name;

}
