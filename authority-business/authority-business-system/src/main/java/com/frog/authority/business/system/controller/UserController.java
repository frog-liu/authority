package com.frog.authority.business.system.controller;

import com.frog.authority.business.system.constant.ApiUrl;
import com.frog.authority.business.system.dto.UserQueryDTO;
import com.frog.authority.business.system.service.IUserService;
import com.frog.authority.business.system.vo.UserManageVO;
import com.frog.authority.common.base.model.PageData;
import com.frog.authority.common.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author liuhuan
 */
@RestController
@Api(value = ApiUrl.USER, tags = "用户信息")
@RequestMapping(ApiUrl.USER)
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping
    @ApiOperation(value = "获取用户管理列表", httpMethod = "GET")
    public Result<PageData<UserManageVO>> list(@Valid UserQueryDTO userQueryDTO) {
        List<UserManageVO> userManageVOList = userService.listUserManage(userQueryDTO);
        return Result.ok(PageData.getInstance(userManageVOList));
    }
}
