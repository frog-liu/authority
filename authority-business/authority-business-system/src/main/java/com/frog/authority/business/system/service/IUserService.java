package com.frog.authority.business.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.frog.authority.business.system.domain.User;


/**
 * @author frog
 */
public interface IUserService extends IService<User> {

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
