package com.example.fanxinglearning.demo10;

import java.lang.reflect.Array;

public class Fruit<T> {
    private T[] array;

    public Fruit(Class<T> clz, int length) {
        // 通过Array.newInstance方法去创建泛型数组
        array = (T[]) Array.newInstance(clz, length);
    }

    /**
     * 放置数组的数据
     *
     * @param index 放置的下标
     * @param item  放置的数据
     */
    public void put(int index, T item) {
        array[index] = item;
    }

    /**
     * 获取数组元素
     *
     * @param index 元素下标
     * @return 对应下标的元素
     */
    public T get(int index) {
        return array[index];
    }

    /**
     * 获取这个类里面的数组
     *
     * @return 泛型数组
     */
    public T[] getArray() {
        return array;
    }
}
