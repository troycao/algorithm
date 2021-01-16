package com.troy.algorithm.sort.bubble;

import com.troy.algorithm.sort.Sort;

import java.util.Arrays;

/**
 * 冒泡排序：
 *  每次循环将最大（小）值筛选出来，放在最后（前）位置
 */
public class BubbleSort<E extends Comparable<E>> extends Sort<E> {

    public static void main(String[] args) {
        Sort sort = new BubbleSort();
        Integer[] array1 = {123,12,45,974,100,99,66};
        sort.sort(array1);
        System.out.println(Arrays.toString(array1));
    }

    @Override
    public void sort() {
        for (int j = array.length; j > 0 ; j--) {
            for (int i = 1; i < array.length; i++) {
                if (compare(i-1, i) > 0){
                    swap(i-1, i);
                }
            }
        }
    }
}
