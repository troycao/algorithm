package com.troy.structure.tree.binarySearchTree;

import com.troy.structure.tree.binaryTree.BinaryTree;

import java.util.Comparator;

/**
 * @author caoqiang
 * @date 2020-11-12 13:53
 * @desc 二叉搜索树
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    private Comparator<E> comparator;

    public BinarySearchTree(){
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator){
        super();
        this.comparator = comparator;
    }

    public void add(E element){
        elementNotNullCheck(element);

        // 添加第一个节点
        if (null == root){
            root = createNode(element, null);
            size ++;
            afterAdd(root);
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

        Node<E> newNode = createNode(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        afterAdd(newNode);
    }

    protected void afterAdd(Node<E> node){}

    protected void afterRemove(Node<E> node){}

    /**
     * 移除节点
     * @param element
     */
    public void remove(E element){
        remove(node(element));
    }

    /***
     * 是否包含element
     * @param element
     * @return
     */
    public boolean contains(E element){
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (null == node) {
            return;
        }

        size--;
        // 删除度为二的节点
        if (node.hasTowChildren()){
            // 找到后继节点
            Node<E> succeed = succeed(node);
            node.element = succeed.element;
            node = succeed;
        }
        // 删除node (节点的度为0或者1的节点)
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (null != replacement){
            replacement.parent = node.parent;
            if (node.parent == null){
                // node度为1的节点,并且为跟节点
                root = replacement;
            } else if (node.parent.left == node) {
                node.parent.left = replacement;
            } else if (node.parent.right == node) {
                node.parent.right = replacement;
            }
            // 删除节点，再平衡
            afterAdd(node);
        } else if (node.parent == null){
            // node是叶子节点,并且是根节点
            root = null;
            // 删除节点，再平衡
            afterAdd(node);
        } else {
            // node是叶子节点，直接删除
            if (node == node.parent.left) {
                node.parent.left = null;
            } else if (node == node.parent.right){
                node.parent.right = null;
            }
            // 删除节点，再平衡
            afterAdd(node);
        }
    }

    private Node<E> node(E element){
        Node<E> node = root;
        while (node != null){
            int compare = compare(element, node.element);
            if (compare == 0){
                return node;
            } else if (compare > 0){
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private int compare(E e1, E e2){
        if (null != comparator) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }


}
