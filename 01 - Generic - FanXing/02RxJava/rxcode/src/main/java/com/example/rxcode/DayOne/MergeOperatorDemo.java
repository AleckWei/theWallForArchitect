package com.example.rxcode.DayOne;

import io.reactivex.Observable;

import static com.example.rxcode.DayOne.CreateOperatorDemo.myObserver;

/**
 * 组合操作符demo
 */
public class MergeOperatorDemo {

    public static void testConcat() {
        // concat就是将传入的被观察者，打包成一个新的被观察者，然后再一起发送出去
        // 但是最多最多只能传入4个被观察者
        Observable.concat(Observable.just("123"), Observable.just("456"))
                .subscribe(myObserver);
    }

    public static void testConcatArray() {
        // concatArray 是 concat 的拓展，可以传入无数个被观察者
        Observable.concatArray(Observable.just("123"), Observable.just("456"),
                Observable.just("789"), Observable.just("012"),
                Observable.just("345"), Observable.just("678"),
                Observable.just("901"), Observable.just("234"),
                Observable.just("567"), Observable.just("890"),
                Observable.just("123"), Observable.just("456"),
                Observable.just("789"), Observable.just("012"))
                .subscribe(myObserver);
    }

}
