package com.troy.structure.queue;

import com.troy.structure.linear.List;
import com.troy.structure.linear.array.ArrayList;

/**
 * @Author troy
 * @Date 2021/1/16 2:38 下午
 * @Version 1.0
 * @Desc: 队列
 */

public class Queue<E> {
    private List<E> list = new ArrayList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }

    /***
     * 入队列
     * @param e
     */
    public void enQueue(E e){
        list.add(e);
    }

    /**
     * 出队列
     * @return
     */
    public E deQueue(){
        return list.remove(0);
    }

    /**
     * 查看队受元素
     * @return
     */
    public E front(){
        return list.get(0);
    }

}
