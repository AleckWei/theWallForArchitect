package com.example.rxcode.day1;


import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.example.rxcode.day1.CreateOperatorDemo.myObserver;

/**
 * 转换操作符测试实例
 * 可以说比创建操作符的重要性更高
 */
public class TransOperatorDemo {

    public static void testMap() {
        // 直接对发射出来的事件进行处理
        // 并且产生新的事件(被观察者)再发射出去
        Observable.just("abcd")
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(String s) throws Exception {
                        return "bbbb";
                    }
                }).subscribe(myObserver);
    }

    public static void testFlatMap() {
        // 这的写法就类似与网络请求的嵌套
        // 即：第二次的请求必须等待第一次请求的结果
        Observable.just("register")
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {
                        // ObservableSource 是 Observable 的最顶层父类
                        return Observable.just("login");
                    }
                }).subscribe(myObserver);
    }

    public static void testConcatMap() {
        Observable.just("11", "222", "3333", "44444", "55555", "66666")
                .flatMap(new Function<String, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(String s) throws Exception {
                        // ObservableSource 是 Observable 的最顶层父类
                        return Observable.just(s + "ssss");
                    }
                }).subscribe(myObserver);
    }

    public static void testBuffer() {
        // buffer相当于建立一个缓冲区域，一次放入n个事件，一次就将这n个事件以数组形式打包发送出来
        Disposable disposable = Observable.just("11", "222", "3333", "44444", "55555", "66666", "777", "88888", "9999", "000000")
                .buffer(4).subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        Log.d("wwj", "======================");
                        for (String str : strings) {
                            Log.d("wwj", str);
                        }
                        Log.d("wwj", "======================");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("wwj", "出错：" + throwable.getMessage());
                    }
                });
    }

}
