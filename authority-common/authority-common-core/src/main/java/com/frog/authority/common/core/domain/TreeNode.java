package com.frog.authority.common.core.domain;

import com.frog.authority.common.core.util.TreeUtils;

/**
 * 树类数据结构标志
 * 可通过{@link TreeUtils} 工具类构建树状结构
 * @author frog
 */
public interface TreeNode {

    /**
     * 是否为最高级父类
     * @return
     */
    boolean isSuperParent();

    /**
     * 获取id
     * @return id
     */
    Long getId();

    /**
     * 获取父类id
     * @return 父类id
     */
    Long getParentId();

    /**
     * 添加子集
     * @param treeNode 字节点
     */
    <T extends TreeNode> void addChildren(T treeNode);
}
