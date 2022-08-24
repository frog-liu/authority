package com.frog.authority.business.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.business.system.constant.Message;
import com.frog.authority.business.system.domain.Role;
import com.frog.authority.business.system.domain.User;
import com.frog.authority.business.system.mapper.UserMapper;
import com.frog.authority.business.system.service.IUserRoleService;
import com.frog.authority.business.system.service.IUserService;
import com.frog.authority.common.core.enums.ExceptionType;
import com.frog.authority.common.core.util.Assert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author frog
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IUserRoleService userRoleService;

    @Override
    public User findByUsername(String username) {
        Assert.notEmpty(ExceptionType.ILLEGAL_ARGUMENT, username, Message.User.USERNAME_NOT_EMPTY);
        User user = this.lambdaQuery().eq(User::getUsername, username).one();
        fillRole(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Assert.notEmpty(ExceptionType.ILLEGAL_ARGUMENT, email, Message.User.EMAIL_NOT_EMPTY);
        User user = this.lambdaQuery().eq(User::getEmail, email).one();
        fillRole(user);
        return user;
    }

    private void fillRole(User user) {
        if (ObjectUtil.isNotNull(user)) {
            List<Role> roleList = userRoleService.listRoleByUserId(user.getId());
            if (!CollectionUtils.isEmpty(roleList)) {
                user.setRoleList(roleList.stream().map(Role::getCode).collect(Collectors.toList()));
            }
        }
    }
}
