package com.example.rxcode;

import android.annotation.SuppressLint;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * rxJava
 * 创建操作符
 * 具体有哪些操作符，请翻看 《各种操作符及其作用》 文档
 */
public class CreateOperatorDemo {
    public static void main() {
        Log.d("wwj", "================");

        CreateOperatorDemo demo1 = new CreateOperatorDemo();
        demo1.test();

        Log.d("wwj", "================");
    }

    @SuppressLint("CheckResult")
    private void test() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 这样就建立了一个 观察者 和 被观察者 订阅关系
                // 这里就是事件产生的地方
                emitter.onNext("wwj1");
                emitter.onNext("wwj2");
                emitter.onNext("wwj3");

                // 加入onError的回调之后，就会发现onComplete不会被回调了
                emitter.onError(new Throwable("你猜是什么错？"));
                // onError和onComplete有你没我
                emitter.onComplete();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {
                // 订阅关系一旦建立，就会回调到这里
                Log.d("wwj", "建立订阅");
            }

            @Override
            public void onNext(Object o) {
                // 上面emitter的 onNext 事件就会回调这里
                Log.d("wwj", "onNext回调：" + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d("wwj", "onError：" + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("wwj", "onComplete");
            }
        });
    }
}
