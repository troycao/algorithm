package com.troy.structure.queue;

import com.troy.structure.linear.List;
import com.troy.structure.linear.link.LinkedList;

/**
 * @Author troy
 * @Date 2021/1/16 2:38 下午
 * @Version 1.0
 * @Desc: 双端队列，前后两端都可以操作元素
 */
public class Deque<E> {

    private List<E> list = new LinkedList<>();

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }

    /**
     * 尾部入队列
     * @param e
     */
    public void enQueueRear(E e){
        list.add(e);
    }

    /***
     * 头部出队列
     * @return
     */
    public E deQueueFront(){
        return list.remove(0);
    }

    /**
     * 头部入队列
     * @param e
     */
    public void enQueueFront(E e){
        list.add(0, e);
    }

    /***
     * 尾部出队列
     * @return
     */
    public E deQueueRear(){
        return list.remove(list.size() - 1);
    }

    /**
     * 获取头部元素
     * @return
     */
    public E front(){
        return list.get(0);
    }

    /***
     * 获取尾部元素
     * @return
     */
    public E rear(){
        return list.get(list.size() - 1);
    }

}
