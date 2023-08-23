package com.frog.authority.security.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.frog.authority.admin.api.UserFeignClient;
import com.frog.authority.admin.api.dto.UserDTO;
import com.frog.authority.common.base.model.Result;
import com.frog.authority.security.domain.UserDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author frog
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserDTO> result = userFeignClient.findUserByUsername(username);
        if (Result.isSuccess(result) && ObjectUtil.isNotNull(result.getBody())) {
            log.error("登录失败,未能找到对应用户[{}]", username);
            throw new UsernameNotFoundException("登录失败,未能找到对应用户");
        }
        UserDetail userDetail = new UserDetail(result.getBody());
        return userDetail;
    }
}
