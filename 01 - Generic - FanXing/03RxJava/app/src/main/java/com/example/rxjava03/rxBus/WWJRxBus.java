package com.example.rxjava03.rxBus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class WWJRxBus {

    private final Subject<Object> mRxBus;

    private static class Holder {
        private static final WWJRxBus BUS = new WWJRxBus();
    }

    private WWJRxBus() {
        // toSerialized 支持线程安全
        mRxBus = PublishSubject.create().toSerialized();
    }

    public static WWJRxBus get() {
        return Holder.BUS;
    }

    public void post(Object event) {
        mRxBus.onNext(event);
    }

    /**
     * 转换成想要转成的Observable
     *
     * @param tClass Observable具体的类
     */
    public <T> Observable<T> toObservable(Class<T> tClass) {
        return mRxBus.ofType(tClass);
    }


}
