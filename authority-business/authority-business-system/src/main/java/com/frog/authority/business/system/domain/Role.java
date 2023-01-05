package com.frog.authority.business.system.domain;

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
    @TableId
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色code
     */
    private String code;

    /**
     * 角色状态
     */
    private StatusEnum status;

}
