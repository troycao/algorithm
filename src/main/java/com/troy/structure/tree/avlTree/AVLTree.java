package com.troy.structure.tree.avlTree;

import com.troy.structure.tree.binarySearchTree.BinarySearchTree;


/**
 * @author caoqiang
 * @date 2020-11-15 13:06
 * @desc
 */
public class AVLTree<E> extends BinarySearchTree<E> {

    @Override
    public void afterAdd(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalance(node)){
                // 更新高度
                updateHeigth(node);
            } else {
                // 重新平衡
                rebalance(node);
                break;
            }
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null){
            if (isBalance(node)){
                // 更新高度
                updateHeigth(node);
            } else {
                // 重新平衡
                rebalance(node);
            }
        }
    }

    /**
     * 重新平衡
     * @param grand
     */
    private void rebalance(Node<E> grand){
        Node<E> parentNode = ((AVLNode)grand).tallerChild();
        Node<E> node = ((AVLNode)parentNode).tallerChild();

        if (parentNode.isLeftChild()) { //L
            if (node.isLeftChild()) { //LL
                rotateRight(grand);
            } else { //LR
                rotateLeft(parentNode);
                rotateRight(grand);
            }
        } else { //R
            if (node.isLeftChild()) { //RL
                rotateRight(parentNode);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
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
    private void rotateLeft(Node<E> grand){
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
    private void rotateRight(Node<E> grand){
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
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child){
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

        // 更新树的高度,注意又顺序，需要先更新低层级的节点，再更新高层级的节点
        updateHeigth(grand);
        updateHeigth(parent);
    }

    private void updateHeigth(Node<E> node){
        ((AVLNode)node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    public boolean isBalance(Node<E> node){
        return Math.abs(((AVLNode)node).balanceFactor()) <= 1;
    }

    protected static class AVLNode<E> extends Node<E> {

        public int height = 1 ;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor(){
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild(){
            int leftHeight = left == null ? 0 : ((AVLNode)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode)right).height;
            if (leftHeight > rightHeight) {
                return left;
            }
            if (leftHeight < rightHeight) {
                return right;
            }
            return isLeftChild() ? left:right;

        }

    }
}
