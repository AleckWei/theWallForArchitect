package com.example.rxcore.abstractClz;

import com.example.rxcore.imp.Function;
import com.example.rxcore.imp.ObservableOnSubscribe;
import com.example.rxcore.imp.ObservableSource;
import com.example.rxcore.imp.Observer;
import com.example.rxcore.operator.ObservableCreate;
import com.example.rxcore.operator.ObservableMap;

/**
 * rxJava被观察者核心抽象类
 * 也是本框架的入口
 */
public abstract class Observable<T> implements ObservableSource<T> {
    @Override
    public void subscribe(Observer<T> observer) {
        // 和谁建立订阅，我是不知道的
        // 如何建立订阅，我也是不知道的
        // 为保证拓展性，交由其他具体的开发人员制定
        // 这里只提供一个抽象方法
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<>(source);
    }

    public <S> ObservableMap<T, S> map(Function<T, S> function) {
        return new ObservableMap<>(this, function);
    }
}
