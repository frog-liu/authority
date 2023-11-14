package com.frog.authority.common.base.model;

import com.frog.authority.common.base.util.TreeUtils;

/**
 * 树状数据结构通用接口
 * 可通过{@link TreeUtils} 工具类构建树状结构
 *
 * @author liuhuan
 */
public interface TreeNode {

    /**
     * 是否为根节点
     *
     * @return 如果是返回true, 否则返回false
     */
    boolean isRoot();

    /**
     * 获取id
     *
     * @return id
     */
    Long getId();

    /**
     * 获取父类id
     *
     * @return 父类id
     */
    Long getParentId();

    /**
     * 添加子节点
     *
     * @param childNode 子节点
     */
    <T extends TreeNode> void addChild(T childNode);
}
