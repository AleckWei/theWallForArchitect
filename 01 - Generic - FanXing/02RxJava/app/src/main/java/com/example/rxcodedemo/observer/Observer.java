package com.example.rxcodedemo.observer;

/**
 * 观察者的上层标准接口
 */
public interface Observer<T> {
    // 有一双眼睛，一直盯着被观察者
    void change(T obj);

}
