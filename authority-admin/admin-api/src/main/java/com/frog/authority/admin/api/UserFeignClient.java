package com.frog.authority.admin.api;

import com.frog.authority.admin.api.constant.ApiConstants;
import com.frog.authority.admin.api.dto.UserDTO;
import com.frog.authority.admin.api.fallback.UserFeignFallbackClient;
import com.frog.authority.common.base.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author frog
 */
@FeignClient(fallback = UserFeignFallbackClient.class)
public interface UserFeignClient {

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping(ApiConstants.User.USER_NAME)
    Result<UserDTO> findUserByUsername(@PathVariable String username);
}
