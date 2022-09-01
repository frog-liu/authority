package com.frog.authority.business.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.business.system.domain.Role;
import com.frog.authority.business.system.mapper.RoleMapper;
import com.frog.authority.business.system.service.IRoleService;
import com.frog.authority.common.core.enums.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author frog
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> list(List<Long> roleIdList, StatusEnum status) {
        return this.lambdaQuery()
                   .in(Role::getId, roleIdList)
                   .eq(ObjectUtil.isNotNull(status), Role::getStatus, status)
                   .list();
    }
}
