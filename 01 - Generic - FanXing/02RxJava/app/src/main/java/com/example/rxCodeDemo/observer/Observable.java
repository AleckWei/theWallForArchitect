package com.example.rxCodeDemo.observer;

/**
 * 上层的标准的被观察者接口
 */
public interface Observable<T> {
    // 注册观察者
    void registerObserver(Observer<T> observer);

    // 移除观察者
    void removeObserver(Observer<T> observer);

    // 产生事件
    void changeObservables(T data);
}
