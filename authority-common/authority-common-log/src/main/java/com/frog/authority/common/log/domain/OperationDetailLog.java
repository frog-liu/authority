package com.frog.authority.common.log.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liuhuan
 */
@Data
@TableName("t_operation_detail_log")
@EqualsAndHashCode(callSuper = true)
public class OperationDetailLog extends BaseEntity {

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 头表id
     */
    private Long headerId;

    /**
     * 请求参数,json格式
     */
    private String params;

    /**
     * 返回结果,json格式
     */
    private String result;

}
