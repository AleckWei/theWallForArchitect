package com.example.rxcore.operator.scheduler;


import android.os.Handler;

/**
 * 使用handler实现线程切换
 */
public class HandlerScheduler extends Scheduler {

    private final Handler handler;

    public HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(handler);
    }

    private static class HandlerWorker implements Worker {

        private final Handler mapper;

        public HandlerWorker(Handler mapper) {
            this.mapper = mapper;
        }

        @Override
        public void scheduler(Runnable r) {
            // 这样就可以切换到主线程当中去执行了
            mapper.post(r);
        }
    }

}
