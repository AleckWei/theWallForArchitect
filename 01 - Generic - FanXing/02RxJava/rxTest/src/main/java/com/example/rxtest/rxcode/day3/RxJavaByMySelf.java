package com.example.rxtest.rxcode.day3;

import android.util.Log;

import com.example.rxcore.abstractClz.Observable;
import com.example.rxcore.imp.Emitter;
import com.example.rxcore.imp.Function;
import com.example.rxcore.imp.ObservableOnSubscribe;
import com.example.rxcore.imp.Observer;


public class RxJavaByMySelf {

    public static void testCreateMyself() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("sb");
                emitter.onError(new Throwable("我是sb"));
                emitter.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i("wwj", "onSubscribe");
            }

            @Override
            public void onNext(String str) {
                Log.d("wwj", "onNext：" + str);
            }

            @Override
            public void onComplete() {
                Log.d("wwj", "onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.w("wwj", "onError" + throwable.getMessage());
            }
        });
    }

    public static void testMapMyself() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                emitter.onNext("sb");
                emitter.onError(new Throwable("我是sb"));
                emitter.onComplete();
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + " After Map Operator";
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                Log.i("wwj", "onSubscribe");
            }

            @Override
            public void onNext(String str) {
                Log.d("wwj", "onNext：" + str);
            }

            @Override
            public void onComplete() {
                Log.d("wwj", "onComplete");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.w("wwj", "onError" + throwable.getMessage());
            }
        });
    }

}
