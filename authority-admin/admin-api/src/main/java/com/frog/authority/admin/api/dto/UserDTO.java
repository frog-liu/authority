package com.frog.authority.admin.api.dto;

import com.frog.authority.common.base.enums.StatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author frog
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 7489088730524357267L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 角色列表
     */
    private List<String> roleList;

}
