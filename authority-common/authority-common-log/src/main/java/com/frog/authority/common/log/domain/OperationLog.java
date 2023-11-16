package com.frog.authority.common.log.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.frog.authority.common.base.enums.StatusEnum;
import com.frog.authority.common.mybatis.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户操作日志记录
 *
 * @author liuhuan
 */
@Data
@TableName("t_operation_log")
@EqualsAndHashCode(callSuper = true)
public class OperationLog extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 概述
     */
    private String summary;

    /**
     * 接口url
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求耗时
     */
    private Long latency;

    /**
     * 成功或者失败
     */
    private StatusEnum status;

}
