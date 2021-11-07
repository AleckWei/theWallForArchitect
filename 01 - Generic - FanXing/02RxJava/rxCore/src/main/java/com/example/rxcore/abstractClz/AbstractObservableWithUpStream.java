package com.example.rxcore.abstractClz;

import com.example.rxcore.imp.ObservableSource;

/**
 * 使用装饰器模式的思想对 被观察者 实现不增加类的数量的前提下
 * 增加被观察者的功能
 */
public abstract class AbstractObservableWithUpStream<T, S> extends Observable<S> {

    protected final ObservableSource<T> source;

    public AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
