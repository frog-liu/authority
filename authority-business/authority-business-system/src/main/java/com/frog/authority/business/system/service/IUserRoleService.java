package com.frog.authority.business.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.authority.business.system.domain.Role;
import com.frog.authority.business.system.domain.UserRole;

import java.util.List;

/**
 * @author frog
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户id查询对应角色
     * @param userId
     * @return 角色列表
     */
    List<Role> listRoleByUserId(Long userId);
}
