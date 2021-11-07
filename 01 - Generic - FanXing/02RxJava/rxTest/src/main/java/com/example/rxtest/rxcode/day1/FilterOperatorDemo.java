package com.example.rxcode.day1;

import io.reactivex.Observable;
import io.reactivex.functions.Predicate;

import static com.example.rxcode.day1.CreateOperatorDemo.myObserver;

/**
 * 过滤操作符demo
 * 把不满足条件的事件从事件序列中去除
 */
public class FilterOperatorDemo {

    /**
     * 这个filter是定义为2的倍数才满足条件
     * 故这里输出的是 2,4,6,8,10
     */
    public static void testFilter() {
        // range从start开始，发送count个事件
        Observable.range(1, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(myObserver);
    }

}
