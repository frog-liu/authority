package com.frog.authority.common.mybatis.util;

import com.frog.authority.common.base.util.RequestUtils;
import com.github.pagehelper.PageHelper;

/**
 * @author liuhuan
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
        Integer pageNum = RequestUtils.getInteger(PAGE_NUM);
        Integer pageSize = RequestUtils.getInteger(PAGE_SIZE);
        if (pageNum != null && pageSize != null) {
            PageHelper.startPage(pageNum, pageSize);
        }
    }

    public static Integer getPageNum() {
        return RequestUtils.getInteger(PAGE_NUM);
    }

    public static Integer getPageSize() {
        return RequestUtils.getInteger(PAGE_SIZE);
    }
}
