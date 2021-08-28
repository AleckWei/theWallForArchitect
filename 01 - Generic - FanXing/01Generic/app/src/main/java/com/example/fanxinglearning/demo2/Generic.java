package com.example.fanxinglearning.demo2;

/**
 * 泛型类的定义
 *
 * @param <T> 泛型标识 -- 类型形参
 *            T ： 创建对象时指定的数据类型
 */
public class Generic<T> {
    //T是由外部指定类的时候来指定的
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}
