package com.frog.authority.common.base.util;

import com.frog.authority.common.base.model.TreeNode;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 树状数据结构工具类
 *
 * @author liuhuan
 */
public class TreeUtils {

    /**
     * 构建树状结构
     *
     * @param nodeList 所有节点
     * @return 构建后节点集合
     */
    public static List<? extends TreeNode> build(List<? extends TreeNode> nodeList) {
        if (!CollectionUtils.isEmpty(nodeList)) {
            List<? extends TreeNode> rootList = listRoot(nodeList);
            for (TreeNode root: rootList) {
                build(root, nodeList, 1);
            }
            return rootList;
        }
        return nodeList;
    }

    /**
     * 筛选出所有根节点
     *
     * @param nodeList 所有节点
     * @return 所有根节点
     */
    private static List<? extends TreeNode> listRoot(List<? extends TreeNode> nodeList) {
        return nodeList.stream().filter(TreeNode::isRoot).collect(Collectors.toList());
    }

    /**
     * 最大递归深度
     */
    private static final int MAX_DEPTH = 100;

    private static void build(TreeNode parent, List<? extends TreeNode> nodeList, int rd) {
        if (rd > MAX_DEPTH) {
            throw new RuntimeException("递归深度过大!");
        }
        for (TreeNode node : nodeList) {
            if (node.getParentId().equals(parent.getId())) {
                build(node, nodeList, rd + 1);
                parent.addChild(node);
            }
        }
    }

}
