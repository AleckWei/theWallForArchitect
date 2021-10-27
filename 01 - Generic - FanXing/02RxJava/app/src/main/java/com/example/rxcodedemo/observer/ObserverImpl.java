package com.example.rxcodedemo.observer;

import android.util.Log;

/**
 * 观察者的具体实现类
 *
 * @param <T> 需要观察的数据的泛型
 */
public class ObserverImpl<T> implements Observer<T> {

    private final String TAG = Observer.class.getSimpleName();

    @Override
    public void change(T obj) {
        Log.d(TAG, "观察者发现了：" + obj.toString());
    }
}
