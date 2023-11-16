package com.frog.authority.common.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.common.log.domain.OperationLog;
import com.frog.authority.common.log.mapper.OperationLogMapper;
import com.frog.authority.common.log.service.IOperationLogService;
import org.springframework.stereotype.Service;

/**
 * Operation log service impl
 *
 * @author liuhuan
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {
}
