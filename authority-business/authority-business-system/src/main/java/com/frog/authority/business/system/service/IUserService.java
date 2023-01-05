package com.frog.authority.business.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.authority.business.system.domain.User;
import com.frog.authority.business.system.dto.UserQueryDTO;
import com.frog.authority.business.system.vo.UserManageVO;

import java.util.List;


/**
 * @author frog
 */
public interface IUserService extends IService<User> {

    /**
     * 用户管理列表查询
     * @param userQueryDTO 查询条件
     * @return 用户列表信息
     */
    List<UserManageVO> listUserManage(UserQueryDTO userQueryDTO);

    /**
     * 根据用户账号查询用户
     * @param username 用户账号
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 根据邮箱查询用户
     * @param email 邮箱
     * @return 用户信息
     */
    User findByEmail(String email);
}
