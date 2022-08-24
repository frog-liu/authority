package com.frog.authority.admin.api;

import com.frog.authority.admin.api.constant.ApiConstants;
import com.frog.authority.admin.api.dto.UserDTO;
import com.frog.authority.admin.api.fallback.UserFeignFallbackClient;
import com.frog.authority.common.core.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author frog
 */
@FeignClient(fallback = UserFeignFallbackClient.class)
public interface UserFeignClient {

    @GetMapping(ApiConstants.User.USER_NAME)
    Result<UserDTO> findUserByUsername(@PathVariable String username);
}
