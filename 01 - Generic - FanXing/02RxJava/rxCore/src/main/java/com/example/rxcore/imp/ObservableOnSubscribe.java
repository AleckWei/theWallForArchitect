package com.example.rxcore.imp;

/**
 * 被观察者与事件之间建立关系的接口
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);

}
