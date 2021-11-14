package com.example.rxcore.operator.scheduler;

/**
 * 线程切换类
 * 提供一个创建worker的抽象方法
 * 具体的切换方法交由实现createWorker的类来实现
 */
public abstract class Scheduler {

    /**
     * 构造线程切换的worker
     *
     * @return worker 执行线程切换的具体实现对象
     */
    public abstract Worker createWorker();

    public interface Worker {

        /**
         * 具体执行线程调度
         *
         * @param r 需要执行任务的线程
         */
        void scheduler(Runnable r);
    }

}
