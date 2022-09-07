package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.authority.common.base.enums.StatusEnum;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frog
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {

    private static final long serialVersionUID = 2103471461379094395L;

    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField
    private String name;

    /**
     * 角色code
     */
    @TableField
    private String code;

    /**
     * 角色状态
     */
    @TableField("status")
    private StatusEnum status;

}
