package com.example.rxcore.imp;

/**
 * rxJava中被观察者的顶层接口
 */
public interface ObservableSource<T> {

    /**
     * 与观察者建立订阅关系
     *
     * @param observer 观察者的实例
     */
    void subscribe(Observer<T> observer);

}
