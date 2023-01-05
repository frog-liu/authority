package com.frog.authority.business.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.frog.authority.common.base.enums.StatusEnum;
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
    @TableId
    private Long id;

    /**
     * 上级部门id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门code
     */
    private String code;

    /**
     * 状态
     */
    private StatusEnum status;

}
