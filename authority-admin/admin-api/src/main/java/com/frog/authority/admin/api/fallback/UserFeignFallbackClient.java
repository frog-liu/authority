package com.frog.authority.admin.api.fallback;

import com.frog.authority.admin.api.UserFeignClient;
import com.frog.authority.admin.api.dto.UserDTO;
import com.frog.authority.common.base.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liuhuan
 */
@Slf4j
@Component
public class UserFeignFallbackClient implements UserFeignClient {

    @Override
    public Result<UserDTO> findUserByUsername(String username) {
        log.error("feign远程调用获取用户信息降级!");
        return Result.fail("获取用户信息失败!");
    }
}
