package com.troy.structure.tree.binaryTree;

import com.troy.tools.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoqiang
 * @date 2020-11-15 12:45
 * @desc
 */
public class BinaryTree<E> implements BinaryTreeInfo {

    public BinaryTree() {

    }

    protected int size;
    protected Node<E> root;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        root = null;
        size = 0;
    }
    public int height(){
        return height(root);
    }

    /**
     * 计算树高度:用递归实现
     * @param node
     * @return
     */
    public int height(Node<E> node){
        if (null == node) {
            return 0;
        }

        return 1 + Math.max(height(node.left) , height(node.right));
    }

    public int heightByLevelTraversal(){
        return heightByLevelTraversal(root);
    }

    /**
     * 计算树高度:层级遍历
     * @param root
     * @return
     */
    public int heightByLevelTraversal(Node<E> root){
        if (null == root){
            return 0;
        }

        int heigh = 0;
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            levelSize--;
            if (null != node.left){
                queue.offer(node.left);
            }

            if (null != node.right){
                queue.offer(node.right);
            }

            // 当levelSize=queue.size时，表示要进入下一层循环
            if (levelSize == 0){
                heigh++;
                levelSize = queue.size();
            }
        }
        return heigh;
    }

    /**
     * 前序遍历:递归实现
     */
    public void preorderTraversal(Visitor<E> visitor){
        preorderTraversal(visitor, root);
    }

    public void preorderTraversal(Visitor<E> visitor, Node<E> node){
        if (null == node || visitor.isStop) {
            return;
        }
        if (visitor.isStop) {
            return;
        }
        visitor.isStop = visitor.visit(node.element);
        preorderTraversal(visitor, node.left);
        preorderTraversal(visitor, node.right);
    }

    /**
     * 中序遍历：递归实现
     */
    public void inorderTraversal(Visitor<E> visitor){
        inorderTraversal(visitor, root);
    }

    public void inorderTraversal(Visitor<E> visitor, Node<E> node){
        if (null == node || visitor.isStop) {
            return;
        }
        inorderTraversal(visitor, node.left);
//        System.out.println(node.element);
        if (visitor.isStop) {
            return;
        }
        visitor.isStop = visitor.visit(node.element);
        inorderTraversal(visitor, node.right);
    }

    /***
     * 后续遍历：递归实现
     */
    public void postOrderTraversal(Visitor<E> visitor){
        postOrderTraversal(visitor, root);
    }

    public void postOrderTraversal(Visitor<E> visitor, Node<E> node){
        if (null == node || visitor.isStop) {
            return;
        }
        postOrderTraversal(visitor, node.left);
        postOrderTraversal(visitor, node.right);
//        System.out.println(node.element);
        if (visitor.isStop) {
            return;
        }
        visitor.isStop = visitor.visit(node.element);
    }

    /***
     * 层序遍历：队列
     */
    public void levelOrderTraversal(Visitor<E> visitor){
        if (null == root || visitor.isStop){
            return;
        }

        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);

        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
//            System.out.println(node.element);
            if (visitor.isStop){
                return;
            }
            visitor.isStop = visitor.visit(node.element);

            if (null != node.left){
                queue.offer(node.left);
            }

            if (null != node.right){
                queue.offer(node.right);
            }
        }
    }

    /**
     * 判断是否为完全二叉树
     * @return
     */
    public boolean isComplate(){
        if (null == root){
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<Node<E>>();
        queue.offer(root);
        boolean isLeaf = false;
        while (!queue.isEmpty()){
            Node<E> node = queue.poll();
            if (isLeaf && !node.isLeaf()){
                return false;
            }
            /*if (node.hasTowChildren()){
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null){
                return false;
            } else {
                isLeaf = true;
                if (node.left != null) {
                    queue.offer(node.left);
                }
            }*/

            if (node.left != null){
                queue.offer(node.left);
            } else if (node.right != null){
                return false;
            }

            if (node.right != null){
                queue.offer(node.right);
            } else {
                isLeaf = true;
            }
        }
        return true;
    }

    /**
     * 计算前驱节点
     * @return
     */
    public Node<E> precursor(Node<E> node){
        if (null == node){
            return null;
        }

        Node<E> leftNode = node.left;
        if (leftNode != null){
            while (leftNode.right != null){
                leftNode = leftNode.right;
            }
            return leftNode;
        }

        while (node.parent != null && node == node.parent.left ){
            node = node.parent;
        }
        return node.parent;
    }

    /**
     * 计算后继节点（和前驱节点相反）
     * @return
     */
    public Node<E> succeed(Node<E> node){
        if (null == node){
            return null;
        }

        Node<E> rightNode = node.right;
        if (rightNode != null){
            while (rightNode.right != null){
                rightNode = rightNode.left;
            }
            return rightNode;
        }

        while (node.parent != null && node == node.parent.right ){
            node = node.parent;
        }
        return node.parent;
    }

    protected Node<E> createNode(E element, Node<E> parent){
        return new Node<E>(element, parent);
    }

    public void elementNotNullCheck(E element){
        if (null == element) {
            throw new IllegalArgumentException("element must be not null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }

    protected static class Node<E>{
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent){
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        public boolean hasTowChildren(){
            return left != null && right != null;
        }

        public boolean isLeftChild(){
            return parent != null && this == parent.left;
        }

        public boolean isRightChild(){
            return parent != null && this == parent.right;
        }
    }

    protected static abstract class Visitor<E> {
        public boolean isStop;
        public abstract boolean visit(E element);
    }

}
