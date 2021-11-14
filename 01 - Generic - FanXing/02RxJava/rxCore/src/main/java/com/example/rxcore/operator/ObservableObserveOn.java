package com.example.rxcore.operator;

import com.example.rxcore.abstractClz.AbstractObservableWithUpStream;
import com.example.rxcore.imp.ObservableSource;
import com.example.rxcore.imp.Observer;
import com.example.rxcore.operator.scheduler.Scheduler;

import java.util.ArrayDeque;
import java.util.Deque;

public class ObservableObserveOn<T> extends AbstractObservableWithUpStream<T, T> {

    private final Scheduler scheduler;

    public ObservableObserveOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
//        observer.onSubscribe();
        Scheduler.Worker worker = scheduler.createWorker();
        // 这里是影响下游的subscribe方法
        source.subscribe(new ObserveOnObserver<>(observer, worker));
    }

    private static class ObserveOnObserver<T> implements Observer<T>, Runnable {

        private final Observer<T> downStream;

        private final Scheduler.Worker worker;

        // 双向队列
        private final Deque<T> queue;

        /**
         * volatile 保证线程安全
         * done 用于表示队列中的任务是否完成
         */
        private volatile boolean done;

        private volatile Throwable error;

        private volatile boolean over;

        public ObserveOnObserver(Observer<T> downStream, Scheduler.Worker worker) {
            this.downStream = downStream;
            this.worker = worker;
            this.queue = new ArrayDeque<>();
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            // Deque 的 offer 方法是不会抛异常的
            // 而 add 方法在这里就会
            queue.offer(t);
            schedule();
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void run() {
            drainNormal();
        }

        /**
         * 从队列中排放事件并处理
         */
        private void drainNormal() {
            final Deque<T> tempQueue = queue;
            final Observer<T> tempObserver = downStream;

            while (true) {
                boolean d = done;
                // poll 方法就是从队列中取出并且删除
                // 不会抛出异常，返回具体数据或者返回null（当没有数据时）
                T t = tempQueue.poll();
                boolean empty = t == null;
                if (checkTerminated(d, empty, tempObserver)) {
                    return;
                }
                if (empty) {
                    break;
                }
                tempObserver.onNext(t);
            }
        }

        private boolean checkTerminated(boolean d, boolean empty, Observer<T> tempObserver) {
            // 当任务处理完毕时
            if (over) {
                // 清空队列并返回true
                queue.clear();
                return true;
            }
            // 如果是处于执行状态
            if (d) {
                Throwable e = error;
                // 当任务出现异常时
                if (e != null) {
                    // 直接调起下游观察者的onError方法
                    tempObserver.onError(e);
                    // 同时将over结束状态置为true
                    // 标识任务结束，剩下的也就不用继续执行了
                    over = true;
                    return true;
                } else if (empty) {
                    // 所有的事件全部执行完毕且没有任何异常
                    tempObserver.onComplete();
                    over = true;
                    return true;
                }
            }
            return false;
        }


        private void schedule() {
            worker.scheduler(this);
        }
    }
}
