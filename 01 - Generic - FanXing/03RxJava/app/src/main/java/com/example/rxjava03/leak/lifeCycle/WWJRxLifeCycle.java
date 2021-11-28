package com.example.rxjava03.leak.lifeCycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * RxLifeCycle 的手写核心实现
 * 生命周期观察者
 * 使用lifeCycle的注解去实现
 */
public class WWJRxLifeCycle<T> implements LifecycleObserver, ObservableTransformer<T, T> {

    // rxJava取消订阅的方法，用以解决rxJava的泄露问题
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.d("wwj", "观察到onDestroy");
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                compositeDisposable.add(disposable);
            }
        });
    }

    public static <T> WWJRxLifeCycle<T> bindLifeCycle(LifecycleOwner lifecycleOwner) {
        WWJRxLifeCycle<T> lifeCycle = new WWJRxLifeCycle<>();
        lifecycleOwner.getLifecycle().addObserver(lifeCycle);
        return lifeCycle;
    }
}
