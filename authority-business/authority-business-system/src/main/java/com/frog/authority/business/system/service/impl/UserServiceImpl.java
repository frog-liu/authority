package com.frog.authority.business.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.frog.authority.business.system.dto.UserQueryDTO;
import com.frog.authority.business.system.service.IUserRoleService;
import com.frog.authority.business.system.constant.Message;
import com.frog.authority.business.system.domain.Role;
import com.frog.authority.business.system.domain.User;
import com.frog.authority.business.system.mapper.UserMapper;
import com.frog.authority.business.system.service.IUserService;
import com.frog.authority.business.system.vo.UserManageVO;
import com.frog.authority.common.base.enums.ValidityStatus;
import com.frog.authority.common.base.exception.BusinessException;
import com.frog.authority.common.base.util.Assert;
import com.frog.authority.common.mybatis.util.PageUtils;
import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * @author frog
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final IUserRoleService userRoleService;

    @Override
    public List<UserManageVO> listUserManage(UserQueryDTO userQueryDTO) {
        Long deptId = userQueryDTO.getDeptId();
        String username = userQueryDTO.getAccount();
        String nickName = userQueryDTO.getNickName();
        String phone = userQueryDTO.getPhone();
        String email = userQueryDTO.getEmail();
        ValidityStatus status = userQueryDTO.getStatus();
        LocalDateTime beginTime = userQueryDTO.getBeginTime();
        LocalDateTime endTime = userQueryDTO.getEndTime();
        PageUtils.startPage();
        List<User> userList = this.lambdaQuery()
                                  .eq(ObjectUtil.isNotNull(deptId), User::getDeptId,deptId)
                                  .like(StringUtils.hasLength(username), User::getAccount, username)
                                  .like(StringUtils.hasLength(nickName), User::getNickName, nickName)
                                  .like(StringUtils.hasLength(phone), User::getPhone, phone)
                                  .like(StringUtils.hasLength(email), User::getEmail, email)
                                  .eq(ObjectUtil.isNotNull(status), User::getStatus, status)
                                  .ge(ObjectUtil.isNotNull(beginTime), User::getCreateTime, beginTime)
                                  .le(ObjectUtil.isNotNull(endTime), User::getCreateTime, endTime)
                                  .list();
        Page<UserManageVO> userManageVOPage = new Page<>();
        BeanUtils.copyProperties(userList, userManageVOPage);
        List<UserManageVO> userManageVOList = new ArrayList<>(userList.size());
        userList.forEach(user -> {
            UserManageVO userManageVO = new UserManageVO();
            BeanUtils.copyProperties(user, userManageVO);
            userManageVOList.add(userManageVO);
        });
        userManageVOPage.addAll(userManageVOList);
        return userManageVOPage;
    }

    @Override
    public User findByUsername(String username) {
        Assert.notEmpty(BusinessException.class, username, Message.User.USERNAME_NOT_EMPTY);
        User user = this.lambdaQuery().eq(User::getAccount, username).one();
        fillRole(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Assert.notEmpty(BusinessException.class, email, Message.User.EMAIL_NOT_EMPTY);
        User user = this.lambdaQuery().eq(User::getEmail, email).one();
        fillRole(user);
        return user;
    }

    private void fillRole(User user) {
        if (ObjectUtil.isNotNull(user)) {
            List<Role> roleList = userRoleService.listRoleByUserId(user.getId());
            if (!CollectionUtils.isEmpty(roleList)) {

            }
        }
    }
}
