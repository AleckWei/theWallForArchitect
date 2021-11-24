package com.example.rxjava03.leak;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjava03.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 解决RxJava中的内存泄露问题
 */
public class LeakActivity extends AppCompatActivity {

    // 可以添加众多disposable，然后统一取消
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        doInThread();
    }

    private void doInThread() {
        Disposable subscribe = Observable.create(new ObservableOnSubscribe<Object>() {
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
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.d("wwj", "Consumer:" + o);
                    }
                });

        compositeDisposable.add(subscribe);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消订阅，释放这个Observable
        compositeDisposable.dispose();
    }
}