package com.frog.authority.common.core.util;

import com.frog.authority.common.core.domain.TreeNode;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author frog
 */
public class TreeUtils {

    /**
     * 构建树状结构
     * @param treeNodeList 所有节点
     * @return 构建后的list
     */
    public static List<? extends TreeNode> build(List<? extends TreeNode> treeNodeList) {
        if (!CollectionUtils.isEmpty(treeNodeList)) {
            List<? extends TreeNode> superParentList = listSuperParent(treeNodeList);
            for (TreeNode superParent: superParentList) {
                build(superParent, treeNodeList);
            }
            return superParentList;
        }
        return treeNodeList;
    }

    private static List<? extends TreeNode> listSuperParent(List<? extends TreeNode> treeNodeList) {
        return treeNodeList.stream().filter(treeNode -> treeNode.isSuperParent()).collect(Collectors.toList());
    }

    private static TreeNode build(TreeNode parent, List<? extends TreeNode> treeNodeList) {
        for (TreeNode treeNode: treeNodeList) {
            if (treeNode.getParentId().equals(parent.getId())) {
                build(treeNode, treeNodeList);
                parent.addChildren(treeNode);
            }
        }
        return parent;
    }

}
