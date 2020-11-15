package com.troy.algorithm;

import com.troy.algorithm.printer.BinaryTrees;
import com.troy.algorithm.tree.binarySearchTree.BinarySearchTree;

/**
 * @author caoqiang
 * @date 2020-10-20 14:49
 * @desc
 */
public class Test {

    public static void main(String[] args) {
        Integer data[] = new Integer[]{
                7,4,2,1,3,5,9,8,11,10,12
        };

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
        for (int i = 0; i < data.length; i++) {
            binarySearchTree.add(data[i]);
        }

//        binarySearchTree.preorderTraversal();
//        binarySearchTree.inorderTraversal();
//        binarySearchTree.postOrderTraversal();

        /*binarySearchTree.levelOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {

                *//*if (element == 2){
                    return true;
                }*//*
                System.out.print("_" + element +"_ ");
                return false
            }
        });*/

//        System.out.println(binarySearchTree.height());
//        System.out.println(binarySearchTree.heightByLevelTraversal());
//        System.out.println(binarySearchTree.isComplate());

        BinaryTrees.println(binarySearchTree);
    }
}
