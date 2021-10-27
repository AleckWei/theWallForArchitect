package com.example.rxcodedemo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者的具体实现
 *
 * @param <T> 被观察的数据泛型
 */
public class ObservableImpl<T> implements Observable<T> {

    private List<Observer<T>> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer<T> observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer<T> observer) {
        observerList.remove(observer);
    }

    /**
     * 被观察者要产生事件，就要告诉所有的观察者
     * 我开始干这件事了
     */
    @Override
    public void changeObservables(T data) {
        for (Observer<T> observer : observerList) {
            observer.change(data);
        }
    }
}
