package com.frog.authority.admin.api.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author liuhuan
 */
@Data
public class DepartmentVO implements Serializable {

    private static final long serialVersionUID = -2599417928118637739L;

    private Long id;

    private String name;

    private String code;

    
}
