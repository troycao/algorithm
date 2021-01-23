package com.troy.structure.linear.queue.circle;

/**
 * @Author troy
 * @Date 2021/1/16 2:54 下午
 * @Version 1.0
 * @Desc: 循环队列
 */
public class CircleQueue<E> {

    private int size;
    /**因为是循环队列，所以要指定头部元素下标*/
    private int front;
    private E[] elements;
    public static final int DEFAULT_CAPACITY = 10;

    public CircleQueue(){
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            elements[(front + i)%elements.length] = null;
        }
        front = 0;
        size = 0;
    }

    public int index(int index){
        return 0;
    }

    public void enQueue(E element){
        ensureCapacity(size + 1);
        elements[(front + size) % elements.length] = element;
        size ++ ;
    }

    public E deQueue(){
        E frontElement = elements[front];
        elements[front] = null;
        front = (front + 1)% elements.length;
        size--;
        return frontElement;
    }

    public E front(){
        return elements[front];
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
