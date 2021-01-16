package com.troy.algorithm.sort;

import com.troy.algorithm.sort.bubble.BubbleSort;
import com.troy.tools.sort.Asserts;
import com.troy.tools.sort.Integers;

import java.util.Arrays;

public class MainSort {

    public static void main(String[] args) {
        Integer[] array = Integers.random(10000, 1, 200);
        testSorts(array, new BubbleSort());
    }

    static void testSorts(Integer[] array, Sort... sorts){
        for (Sort sort:sorts){
            Integer[] newArray = Integers.copy(array);
            sort.sort(newArray);
            Asserts.test(Integers.isAscOrder(newArray));
        }
        Arrays.sort(sorts);

        for (Sort sort:sorts){
            System.out.println(sort);
        }
    }
}
