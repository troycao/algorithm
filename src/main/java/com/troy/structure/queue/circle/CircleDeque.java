package com.troy.structure.queue.circle;

import javax.security.auth.login.CredentialNotFoundException;

/**
 * @Author troy
 * @Date 2021/1/16 2:54 下午
 * @Version 1.0
 * @Desc: 循环双端队列
 */
public class CircleDeque<E> {

    private int size;
    private int front;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[(i + front)%elements.length] = null;
        }
        front = 0;
        size = 0;
    }

    /**
     * 尾部入队列
     * @param element
     */
    public void enQueueRear(E element){
        ensureCapacity(size + 1);
        elements[(front + size) % elements.length] = element;
        size ++ ;
    }

    /***
     * 头部出队列
     * @return
     */
    public E deQueueFront(){
        E frontElement = elements[front];
        elements[front] = null;
        front = (front + 1)% elements.length;
        size--;
        return frontElement;
    }

    /**
     * 头部入队列
     * @param e
     */
    public void enQueueFront(E e){
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = e;
        size++;
    }

    /***
     * 尾部出队列
     * @return
     */
    public E deQueueRear(){
        int rearIndex = index(size - 1);
        E rear = elements[rearIndex];
        elements[rearIndex] = null;
        size--;
        return rear;
    }

    /**
     * 获取头部元素
     * @return
     */
    public E front(){
        return elements[front];
    }

    /***
     * 获取尾部元素
     * @return
     */
    public E rear(){
        return elements[(front + size)%elements.length - 1];
    }

    /**
     * 根据下标获取元素在数组中真实的位置
     * @return
     */
    private int index(int index){
        index += front;
        if (index < 0){
            return index + elements.length;
        }
        return index%elements.length;
    }

    /***
     * 极致的优化，取摸效率低，可以改为减法，
     * 但是需要满足相关条件（index>=0 elements.length>0 index < 2*elements.length）
     * @param index
     * @return
     */
    private int index1(int index) {
        index += front;
        return index - (index >= elements.length ? elements.length : 0);
    }

    /***
     * 动态扩容
     * @param capacity
     */
    public void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + size)%elements.length];
        }
        elements = newElements;

        //重置front
        front = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("capcacity=").append(elements.length)
                .append(" size=").append(size)
                .append(" front=").append(front)
                .append(", [");
        for (int i = 0; i < elements.length; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(elements[i]);
        }
        string.append("]");
        return string.toString();
    }

}
