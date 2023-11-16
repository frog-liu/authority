package com.frog.authority.common.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.common.log.domain.OperationDetailLog;
import com.frog.authority.common.log.mapper.OperationDetailLogMapper;
import com.frog.authority.common.log.service.IOperationDetailLogService;
import org.springframework.stereotype.Service;

/**
 * Operation detail log service impl
 *
 * @author liuhuan
 */
@Service
public class OperationDetailLogServiceImpl extends ServiceImpl<OperationDetailLogMapper, OperationDetailLog> implements IOperationDetailLogService {
}
