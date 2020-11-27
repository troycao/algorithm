package com.troy.algorithm.sort.bubble;

/**
 * 冒泡排序：
 *  每次循环将最大（小）值筛选出来，放在最后（前）位置
 */
public class BubbleSort {

    Integer[] array = {123,12,45,974,100,99,66};
    public static void bubbleSort1(Integer[] array){
        for (int i = 1; i < array.length; i++) {
//            if (array[i] <)
        }
    }

    public static void main(String[] args) {
        BubbleSort.test();


    }

    public static boolean test(){
        int i = 0;
        for(;;){
            i++;
            if (i == 10){
                return false;
            }
            System.out.println(i);
        }
    }
}
