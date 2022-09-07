package com.frog.authority.business.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.authority.business.system.domain.Role;
import com.frog.authority.common.base.enums.StatusEnum;

import java.util.List;

/**
 * @author frog
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据角色id和状态查询角色
     * @param roleIdList 角色id列表
     * @param status 角色状态
     * @return 角色列表
     */
    List<Role> list(List<Long> roleIdList, StatusEnum status);
}
