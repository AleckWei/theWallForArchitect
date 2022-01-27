package com.example.thread;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

public class UserThreadLocal {

    // 保证每个线程获取到本类的对象时，都会有一个初始的变量且默认值为 1
    static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @NonNull
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    private void startThreadArray() {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new TestThread(i));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static class TestThread implements Runnable {

        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " start running");
            Integer integer = threadLocal.get();
            integer += id;
            threadLocal.set(integer);
            System.out.println(Thread.currentThread().getName() + " value： " + threadLocal.get());
            threadLocal.remove();
        }
    }

    @Test
    public void main() {
        UserThreadLocal userThreadLocal = new UserThreadLocal();
        userThreadLocal.startThreadArray();
    }


}
