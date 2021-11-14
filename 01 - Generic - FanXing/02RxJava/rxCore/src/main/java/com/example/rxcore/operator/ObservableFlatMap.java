package com.example.rxcore.operator;

import com.example.rxcore.abstractClz.AbstractObservableWithUpStream;
import com.example.rxcore.imp.Function;
import com.example.rxcore.imp.ObservableSource;
import com.example.rxcore.imp.Observer;

/**
 * flatMap 在 rxJava 的源码当中做了很多的优化以及判断
 * 这里仅仅是它的简单实现以及核心部分
 */
public class ObservableFlatMap<T, S> extends AbstractObservableWithUpStream<T, S> {

    private Function<T, ObservableSource<S>> function;

    public ObservableFlatMap(ObservableSource<T> source, Function<T, ObservableSource<S>> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<S> observer) {
        MergeObserver<T, S> tsMergeObserver = new MergeObserver<>(observer, this.function);
        source.subscribe(tsMergeObserver);
    }

    private static class MergeObserver<T, S> implements Observer<T> {

        private final Observer<S> downStream;

        private final Function<T, ObservableSource<S>> mapper;

        public MergeObserver(Observer<S> downStream, Function<T, ObservableSource<S>> mapper) {
            this.downStream = downStream;
            this.mapper = mapper;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            // flatMap的onNext当中，创建了一个新的被观察者
            ObservableSource<S> observable = mapper.apply(t);
            observable.subscribe(new Observer<S>() {
                @Override
                public void onSubscribe() {

                }

                @Override
                public void onNext(S s) {
                    downStream.onNext(s);
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
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
