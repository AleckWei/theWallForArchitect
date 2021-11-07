package com.example.rxcore.imp;

/**
 * 事件发射器接口
 * 目的是将事件本身和被观察者进行解耦
 */
public interface Emitter<T> {

    /**
     * 事件流的主要方法
     */
    void onNext(T t);

    /**
     * 事件完成的事件
     * 为rxJava事件流的末尾
     */
    void onComplete();

    /**
     * 当中途出现异常时调用
     */
    void onError(Throwable throwable);

}
