package com.troy.algorithm;

import com.troy.algorithm.printer.BinaryTrees;
import com.troy.algorithm.tree.avlTree.AVLTree;

/**
 * @author caoqiang
 * @date 2020-10-20 14:49
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        Integer data[] = new Integer[]{
                35, 23, 95, 45, 6, 15, 92, 61, 87, 24, 16
        };

        AVLTree<Integer> avlTree = new AVLTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            avlTree.add(data[i]);
        }

        BinaryTrees.println(avlTree);

        avlTree.add(30);
        BinaryTrees.println(avlTree);
    }
}
