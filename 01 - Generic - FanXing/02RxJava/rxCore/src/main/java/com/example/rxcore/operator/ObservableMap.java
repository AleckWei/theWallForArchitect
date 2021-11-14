package com.example.rxcore.operator;

import com.example.rxcore.abstractClz.AbstractObservableWithUpStream;
import com.example.rxcore.imp.Function;
import com.example.rxcore.imp.ObservableSource;
import com.example.rxcore.imp.Observer;

public class ObservableMap<T, S> extends AbstractObservableWithUpStream<T, S> {

    private Function<T, S> function;

    public ObservableMap(ObservableSource<T> source, Function<T, S> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<S> observer) {
        MapObservable<T, S> mapObservable = new MapObservable<>(observer, function);
        source.subscribe(mapObservable);
    }

    static class MapObservable<T, S> implements Observer<T> {
        // 下游观察者
        private final Observer<S> downStream;
        // 事件类型转换器，map操作符的核心
        private final Function<T, S> mapper;

        public MapObservable(Observer<S> downStream, Function<T, S> function) {
            this.downStream = downStream;
            mapper = function;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            // 上游事件的被观察者传入，通过mapper的apply方法转成下游被观察者的类型
            S downStreamObserver = mapper.apply(t);
            // 再由下游观察者发送事件
            downStream.onNext(downStreamObserver);
        }

        @Override
        public void onComplete() {
            downStream.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            downStream.onError(throwable);
        }
    }

}
