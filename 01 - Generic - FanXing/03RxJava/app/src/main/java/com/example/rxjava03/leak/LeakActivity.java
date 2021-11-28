package com.example.rxjava03.leak;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjava03.R;
import com.example.rxjava03.leak.lifeCycle.WWJRxLifeCycle;
import com.example.rxjava03.leak.transformer.SchedulerTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * 解决RxJava中的内存泄露问题
 */
public class LeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

//        doInThread();
//        subjectTest();
//        behaviorSubjectTest();
//        replaySubjectTest();
        publishSubjectTest();
    }

    @SuppressLint("CheckResult")
    private void doInThread() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 这里来模拟网络请求的过程
                // 使用sleep来做一个5s的耗时操作
                Log.d("wwj", "开始请求数据");
                Thread.sleep(5000);
                Log.d("wwj", "请求数据成功");
                emitter.onNext("success");
                emitter.onComplete();
            }
        })
                // compose 主要想实现代码复用
                .compose(new SchedulerTransformer<>())
                .compose(WWJRxLifeCycle.bindLifeCycle(this))
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("wwj", "获取到的数据：" + o);
                    }
                });
    }

    /**
     * AsyncSubject 只会接受最后一个onNext发送的事件
     * <p>
     * res => 5
     */
    @SuppressLint("CheckResult")
    private void subjectTest() {
        AsyncSubject<Object> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("Hello wwj0");
        asyncSubject.onNext("Hello wwj1");

        asyncSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("wwj", "订阅的事件结果：" + o);
            }
        });

        asyncSubject.onNext("Hello wwj2");
        asyncSubject.onNext("Hello wwj3");
        asyncSubject.onNext("Hello wwj4");
        asyncSubject.onNext("Hello wwj5");
        asyncSubject.onComplete();
        // AsyncSubject 必须要发射onComplete或者onError才会真正的去发射事件
        // 而且也只会接受到最后一个onNext事件
    }

    /**
     * behaviorSubject 会发送订阅前的最后一条数据
     * 以及订阅后的所有数据
     * <p>
     * res => 2、3、4
     */
    @SuppressLint("CheckResult")
    private void behaviorSubjectTest() {
        BehaviorSubject<Object> objectBehaviorSubject = BehaviorSubject.create();
        objectBehaviorSubject.onNext("Hello wwj");
        objectBehaviorSubject.onNext("Hello wwj1");
        objectBehaviorSubject.onNext("Hello wwj2");

        objectBehaviorSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("wwj", "订阅的事件结果：" + o);
            }
        });
        objectBehaviorSubject.onNext("Hello wwj3");
        objectBehaviorSubject.onNext("Hello wwj4");
        objectBehaviorSubject.onComplete();
    }

    /**
     * ReplaySubject 会接受所有发送的数据，不论是之前或之后
     * <p>
     * res => 0、1、2、3、4、5
     */
    @SuppressLint("CheckResult")
    private void replaySubjectTest() {
        ReplaySubject<Object> objectReplaySubject = ReplaySubject.create();
        objectReplaySubject.onNext("Hello wwj");
        objectReplaySubject.onNext("Hello wwj1");

        objectReplaySubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("wwj", "订阅的事件结果：" + o);
            }
        });

        objectReplaySubject.onNext("Hello wwj2");
        objectReplaySubject.onNext("Hello wwj3");
        objectReplaySubject.onNext("Hello wwj4");
        objectReplaySubject.onNext("Hello wwj5");
        objectReplaySubject.onComplete();
    }

    /**
     * PublishSubject只会处理订阅后发送的数据
     * <p>
     * res => 3、4、5
     */
    private void publishSubjectTest() {
        PublishSubject<Object> publishSubject = PublishSubject.create();
        publishSubject.onNext("Hello wwj");
        publishSubject.onNext("Hello wwj1");
        publishSubject.onNext("Hello wwj2");

        publishSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d("wwj", "订阅的事件结果：" + o);
            }
        });

        publishSubject.onNext("Hello wwj3");
        publishSubject.onNext("Hello wwj4");
        publishSubject.onNext("Hello wwj5");
        publishSubject.onComplete();

    }
}