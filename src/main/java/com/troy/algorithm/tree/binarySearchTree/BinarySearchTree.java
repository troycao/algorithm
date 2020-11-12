package com.troy.algorithm.tree.binarySearchTree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author caoqiang
 * @date 2020-11-12 13:53
 * @desc
 */
public class BinarySearchTree<E> {

    private int size;

    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree(){
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator){
        this.comparator = comparator;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){

    }

    public void add(E element){
        elementNotNullCheck(element);

        // 添加第一个节点
        if (null == root){
            root = new Node<E>(element, null);
            size ++;
            return;
        }

        // 添加非根节点
        Node<E> node = root;
        Node<E> parent = root;
        int compare = 0;
        while (null != node){
            parent = node;
            compare = compare(element, node.element);
            if (compare > 0){
                node = node.right;
            } else if (compare < 0){
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        Node<E> newNode = new Node<E>(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
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

    public void remove(E element){

    }

    public void contains(E element){

    }

    public void elementNotNullCheck(E element){
        if (null == element) {
            throw new IllegalArgumentException("element must be not null");
        }
    }

    private int compare(E e1, E e2){
        if (null != comparator) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }

    private static class Node<E>{
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent){
            this.element = element;
            this.parent = parent;
        }
    }

    public static abstract class Visitor<E> {
        boolean isStop;
        protected abstract boolean visit(E element);
    }

}
