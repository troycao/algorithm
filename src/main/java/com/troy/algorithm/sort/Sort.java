package com.troy.algorithm.sort;

import java.text.DecimalFormat;

public abstract class Sort<E extends Comparable> implements Comparable<Sort<E>> {
    protected E[] array;
    private int compareCount;
    private int swapCount;
    private long time;
    private DecimalFormat format = new DecimalFormat("#.00");

    /**
     * 将公共方法抽到抽象类中，节省代码量
     * @param array
     */
    public void sort(E[] array){
        if (array == null || array.length < 2) return;
        this.array = array;
        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    public abstract void sort();

    /**
     * 算法比较：
     * 先按照时间比较，然后根据比较次数，最后根据交换次数
     * @param o
     * @return
     */
    @Override
    public int compareTo(Sort<E> o) {
        int result = (int) (time - o.time);
        if (result != 0) return result;

        result = compareCount - o.compareCount;
        if (result != 0) return result;

        return swapCount - o.swapCount;
    }

    /**
     * 比较元素下标
     * @param i1
     * @param i2
     * @return
     */
    public int compare(int i1,int i2){
        compareCount++;
        return array[i1].compareTo(array[i2]);
    }

    /**
     * 比较元素指
     * @param e1
     * @param e2
     * @return
     */
    public int compare(E e1, E e2){
        compareCount++;
        return e1.compareTo(e2);
    }

    /**
     * 交换元素
     * @param i1
     * @param i2
     */
    public void swap(int i1, int i2){
        swapCount++;
        E tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时:" + time/1000 + "s";
        String compareCountStr = "比较:" + numberFormat(compareCount);
        String swapCountStr = "交换:" + numberFormat(swapCount);
        return "{" + this.getClass().getSimpleName() + "} \n" +
                timeStr + "\t" +
                compareCountStr + "\t" +
                swapCountStr + "\t";
    }

    private String numberFormat(int number){
        if (number < 10000) return "" + number;

        if (number < 100000000) return format.format(number/10000) + "万";
        return format.format(number/100000000) + "亿";
    }
}
