package com.troy.structure.tree.balance.avlTree;

import com.troy.structure.tree.balance.BalanceBinarySearchTree;


/**
 * @author caoqiang
 * @date 2020-11-15 13:06
 * @desc:平衡二叉树
 */
public class AVLTree<E> extends BalanceBinarySearchTree<E> {

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

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

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
