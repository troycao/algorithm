package com.troy.structure.tree.balance;

import com.troy.structure.tree.binarySearchTree.BinarySearchTree;

import java.util.Comparator;

/**
 * @Author troy
 * @Date 2021/1/31 1:41 下午
 * @Version 1.0
 * @Desc: 平衡二叉搜索树
 */
public class BalanceBinarySearchTree<E> extends BinarySearchTree<E> {

    public BalanceBinarySearchTree(){
        this(null);
    }

    public BalanceBinarySearchTree(Comparator<E> comparator){
        super(comparator);
    }

    /**
     * 左旋转
     * @param grand 传入的是第一个不平衡的节点，也就是添加的节点的祖父节点，
     *              那么根据旋转的方向可以获得添加节点的父节点：
     *              1.对节点进行操作
     *              2.更新各节点的父节点
     *              3.更新各节点的高度
     *
     */
    protected void rotateLeft(Node<E> grand){
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /***
     * 右旋转:原理同左旋转
     * @param grand
     */
    protected void rotateRight(Node<E> grand){
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    /**
     * 旋转将节点指向父节点，更新节点高度
     * @param grand
     * @param parent
     * @param child
     */
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
        // 将parent更新为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()){
            grand.parent.left = parent;
        } else if (grand.isRightChild()){
            grand.parent.right = parent;
        } else {
            // 既不是左节点，又不是又节点，只能是根节点
            root = parent;
        }

        // 将child的父节点更新为grand
        if (child != null){
            child.parent = grand;
        }

        // 将grand的父节点更新为parent节点
        grand.parent = parent;
    }

}
