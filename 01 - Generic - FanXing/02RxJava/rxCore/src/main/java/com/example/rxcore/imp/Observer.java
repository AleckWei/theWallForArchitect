package com.example.rxcore.imp;

/**
 * rxJava中观察者的顶层接口
 */
public interface Observer<T> {

    /**
     * 建立订阅关系后就回调
     */
    void onSubscribe();

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
