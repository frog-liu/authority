package com.frog.authority.common.base.util;

import com.frog.authority.common.base.enums.IBaseEnum;

import java.util.Set;

/**
 * @author liuhuan
 */
public class ReflectUtilsTest {

    public static void main(String[] args) {
        testGetSubTypesOf();
    }

    public static void testGetSubTypesOf() {
        Set<Class<? extends IBaseEnum>> subEnums = ReflectUtils.getSubTypesOf(IBaseEnum.class);
        for (Class<? extends IBaseEnum> subEnum : subEnums) {
            System.out.println(IBaseEnum.getByValue(1, subEnum));
        }
    }
}
