package com.example.rxcore.operator.scheduler;

import java.util.concurrent.ExecutorService;

public class NewThreadScheduler extends Scheduler {

    private final ExecutorService executorService;

    public NewThreadScheduler(ExecutorService mapper) {
        this.executorService = mapper;
    }

    @Override
    public Worker createWorker() {
        return new NewThreadWorker(executorService);
    }

    private static class NewThreadWorker implements Worker {

        private final ExecutorService mapper;

        public NewThreadWorker(ExecutorService mapper) {
            this.mapper = mapper;
        }

        @Override
        public void scheduler(Runnable r) {
            mapper.execute(r);
        }
    }

}
