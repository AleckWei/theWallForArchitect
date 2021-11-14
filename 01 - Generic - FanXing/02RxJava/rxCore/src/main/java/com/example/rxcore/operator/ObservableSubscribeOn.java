package com.example.rxcore.operator;

import com.example.rxcore.abstractClz.AbstractObservableWithUpStream;
import com.example.rxcore.imp.ObservableSource;
import com.example.rxcore.imp.Observer;
import com.example.rxcore.operator.scheduler.Scheduler;

/**
 * 线程切换操作符
 *
 * @param <T> 切换前后的泛型，由于仅是切换线程，不涉及数据类型的改变
 *            故这里只使用了一种泛型
 */
public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T, T> {

    private final Scheduler scheduler;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        // onSubscribe需要在这里调用，这里才是原线程
        observer.onSubscribe();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.scheduler(new SubscribeTask(new SubscribeOnObserver<T>(observer)));
    }

    private static class SubscribeOnObserver<T> implements Observer<T> {
        private final Observer<T> downStream;

        private SubscribeOnObserver(Observer<T> downStream) {
            this.downStream = downStream;
        }

        @Override
        public void onSubscribe() {
            // 这里不要对onSubscribe做线程切换了
            // 这里是需要由上游的线程决定的
        }

        @Override
        public void onNext(T t) {
            downStream.onNext(t);
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

    private class SubscribeTask implements Runnable {

        private final SubscribeOnObserver<T> parent;

        public SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        public void run() {
            source.subscribe(parent);
        }
    }
}
