package com.frog.authority.business.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.business.system.domain.Department;
import com.frog.authority.business.system.mapper.DepartmentMapper;
import com.frog.authority.business.system.service.IDepartmentService;
import org.springframework.stereotype.Service;

/**
 * @author frog
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
}
