package com.example.rxCodeDemo.observer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxCodeDemo.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // 设置多个观察者
        Observer<Integer> observer1 = new ObserverImpl<>();
        Observer<Integer> observer2 = new ObserverImpl<>();
        Observer<Integer> observer3 = new ObserverImpl<>();

        // 只设置一个被观察者
        Observable<Integer> observable = new ObservableImpl<>();
        observable.registerObserver(observer1);
        observable.registerObserver(observer2);
        observable.registerObserver(observer3);

        // 被观察者产生事件
        observable.changeObservables(23333);

        // 对比：
        // 标准的：
        //      多个观察者，一个被观察者，观察者多个就要注册多次，需要被观察者主动产生事件
        // rxJava的：
        //      一个观察者，多个被观察者，观察者只注册一次（一旦注册，马上分发事件），就不需要主动分发事件
    }
}