package com.frog.authority.business.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.business.system.domain.Role;
import com.frog.authority.business.system.domain.UserRole;
import com.frog.authority.business.system.mapper.UserRoleMapper;
import com.frog.authority.business.system.service.IRoleService;
import com.frog.authority.business.system.service.IUserRoleService;
import com.frog.authority.common.base.enums.StatusEnum;
import com.frog.authority.common.base.util.Assert;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author frog
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    private final IRoleService roleService;

    @Override
    public List<Role> listRoleByUserId(Long userId) {
        Assert.notNull(userId, "查询用户角色失败,用户id不能为空!");
        List<UserRole> userRoleList = listByUserId(userId);
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return roleService.list(roleIdList, StatusEnum.VALID);
    }

    private List<UserRole> listByUserId(@NotNull Long userId) {
        return this.lambdaQuery().eq(UserRole::getUserId, userId).list();
    }
}
