package com.example.rxcode.day1;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能操作符demo
 */
public class ToolOperatorDemo {

    public static void testSubscribeOn() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 创建被观察者，同时发送事件

                //1.先判断线程
                Log.d("wwj", "subscribe ..." + Thread.currentThread());

                // 耗时2s再发送
//                Thread.sleep(2000);
                emitter.onNext("111");
                emitter.onNext("222");
                emitter.onNext("333");

                emitter.onComplete();
            }
        })
                // 主要决定执行subscribe方法所处的线程
                // 也就是产生事件发射事件所在的线程
                .subscribeOn(Schedulers.io())
                // 决定下游事件被处理时所处的线程
                // AndroidSchedulers.mainThread()底层是通过Handler实现
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Object, Object>() {
                    @Override
                    public Object apply(Object o) throws Exception {
                        Log.d("wwj", "map apply ..." + Thread.currentThread());
                        return "bbb123123";
                    }
                })
                // 在这里又切换一次线程，看看会不会影响它的下游
                .observeOn(Schedulers.io())
                // 测试结果会发现，确实调一次切换一次

                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("wwj", "onSubscribe ..." + Thread.currentThread());
                    }

                    @Override
                    public void onNext(Object o) {
                        Log.d("wwj", "onNext ..." + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("wwj", "onError ..." + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("wwj", "onComplete ..." + Thread.currentThread());
                    }
                });
    }

}
