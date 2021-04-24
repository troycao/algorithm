package com.troy.structure.tree.balance.redBlackTree;

import com.troy.structure.tree.binarySearchTree.BinarySearchTree;

import java.util.Comparator;

/**
 * @Author troy
 * @Date 2021/1/31 12:52 下午
 * @Version 1.0
 * @Desc: 红黑树
 */
public class RedBlackTree<E> extends BinarySearchTree<E> {

    private Comparator<E> comparator;

    public RedBlackTree(){
        this(null);
    }

    public RedBlackTree(Comparator<E> comparator){
        super();
        this.comparator = comparator;
    }

    private static class RedBlackNode<E> extends Node<E>{

        public RedBlackNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }


}
