package com.frog.authority.common.mybatis.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 所有实体类基类
 *
 * @author frog
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 6522055280511275208L;

    /**
     * 创建人id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 最近一次更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long lastUpdateBy;

    /**
     * 最近一次更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastUpdateTime;

}
