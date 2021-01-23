package com.troy.structure.linear.list.array;

import com.troy.structure.linear.list.AbstractList;

/**
 * @author Troy
 * @date 2020-11-27 14:17
 * @desc: 动态数组（包含缩容操作）
 */
public class ArrayListWithReduce<E> extends AbstractList<E> {

    /**
     * 所有元素
     */
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;

    /**
     * @param capaticy 数组初始化大小
     */
    public ArrayListWithReduce(int capaticy){
        capaticy = (capaticy < DEFAULT_CAPACITY)? DEFAULT_CAPACITY: capaticy;
        elements = (E[])new Object[capaticy];
    }

    public ArrayListWithReduce(){
        this(DEFAULT_CAPACITY);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 获取index位置的元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        rangeCheck(index);

        return elements[index];
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);

        E old = elements[index];
        elements[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i-1] = elements[i];
        }
        elements[size] = null;
        size--;
        trim();
        return old;
    }

    /**
     * 缩容
     */
    private void trim() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;
        if (size > (newCapacity) || oldCapacity <=DEFAULT_CAPACITY) return;

        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
        System.out.println("数组:" + oldCapacity + "缩容为" + newCapacity);
    }

    @Override
    public int indexOf(E element) {
        if (element == null){
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 扩容
     */
    private void ensureCapacity(int capacity){
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // 新容量为旧容量的1.5倍
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements =  (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        System.out.println("数组:" + oldCapacity + "扩容为" + newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size=").append(size).append(", [");
        for (int i = 0; i < size; i++) {
            if (i != 0){
                stringBuilder.append(", ");
            }
            stringBuilder.append(elements[i]);
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
