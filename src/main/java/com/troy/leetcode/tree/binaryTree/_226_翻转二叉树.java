package com.troy.leetcode.tree.binaryTree;

import org.omg.CORBA.TRANSACTION_MODE;

import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoqiang
 * @date 2020-11-13 14:07
 * @desc 翻转二叉树
 */
public class _226_翻转二叉树 {

    /*public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }*/


    public TreeNode invertTree(TreeNode root) {
        if (null == root) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);

        while (!queue.isEmpty()){
            TreeNode treeNode = queue.poll();
            TreeNode tempNode = treeNode.left;
            treeNode.left = treeNode.right;
            treeNode.right = tempNode;

            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }

        return root;
    }


    /**
     * Definition for a binary tree node.
     * */
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}


}
