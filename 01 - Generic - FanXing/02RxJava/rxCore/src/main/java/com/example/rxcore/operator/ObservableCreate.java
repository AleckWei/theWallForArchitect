package com.example.rxcore.operator;

import com.example.rxcore.abstractClz.Observable;
import com.example.rxcore.imp.Emitter;
import com.example.rxcore.imp.ObservableOnSubscribe;
import com.example.rxcore.imp.Observer;

/**
 * rxJava的create操作符
 */
public class ObservableCreate<T> extends Observable<T> {

    private final ObservableOnSubscribe<T> observableOnSubscribe;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        observableOnSubscribe = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        observer.onSubscribe();
        // 初始化事件发射器
        CreateEmitter<T> createEmitter = new CreateEmitter<>(observer);
        // 建立被观察者和发射器的联系
        observableOnSubscribe.subscribe(createEmitter);
    }

    static class CreateEmitter<T> implements Emitter<T> {

        Observer<T> observer;
        boolean done;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if (done) {
                return;
            }
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            if (done) {
                return;
            }
            observer.onComplete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if (done) {
                return;
            }
            observer.onError(throwable);
            done = true;
        }
    }

}
