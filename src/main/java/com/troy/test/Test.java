package com.troy.test;

import com.troy.structure.linear.list.List;
import com.troy.structure.linear.list.link.single.SingleLinkedList;

/**
 * @author caoqiang
 * @date 2020-10-20 14:49
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        /*Integer data[] = new Integer[]{
                35, 23, 95, 45, 6, 15, 92, 61, 87, 24, 16
        };

        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        BinaryTrees.println(avlTree);

        avlTree.add(30);
        BinaryTrees.println(avlTree);*/

        List list = new SingleLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(list);

        list.remove(4);
        System.out.println(list);

    }
}
