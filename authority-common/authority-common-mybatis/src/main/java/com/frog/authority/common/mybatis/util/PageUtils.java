package com.frog.authority.common.mybatis.util;

import com.frog.authority.common.base.util.RequestUtils;
import com.github.pagehelper.PageHelper;

/**
 * 数据库分页工具类
 *
 * @author frog
 */
public class PageUtils {

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    public static void startPage() {
        Integer pageNum = getPageNum();
        Integer pageSize = getPageSize();
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    public static Integer getPageNum() {
        return RequestUtils.getIntegerParam(PAGE_NUM);
    }

    public static Integer getPageSize() {
        return RequestUtils.getIntegerParam(PAGE_SIZE);
    }
}
