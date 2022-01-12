package com.example.thread;


import android.util.Log;

import org.junit.Test;
import org.junit.internal.management.ManagementFactory;
import org.junit.internal.management.ThreadMXBean;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class OnlyMain {

    /**
     * 方式1：继承自Thread类
     */

    /**
     * 方式2：实现Runnable接口（因为java是单继承的，但是接口却可以多实现）
     */
    private static class UserRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("通过实现 Runnable 接口启动的线程");
        }
    }

    /**
     * 方式3：继承Callable接口，与Runnable不同的是，这里是可以指定返回值的
     */
    private static class UserCallable implements Callable<String> {
        @Override
        public String call() {
            System.out.println("通过实现 Callable 接口启动的线程");
            return "我是wwj";
        }
    }

    @Test
    public void main() {
        // java提供的，虚拟机线程管理的接口
        // 通过 ThreadMXBean 类可以获取当前有多少个线程
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        boolean threadCpuTimeSupported = threadMXBean.isThreadCpuTimeSupported();
        System.out.println("main方法执行");

        // runnable的执行
        UserRunnable userRunnable = new UserRunnable();
        new Thread(userRunnable).start();
        Thread thread = new Thread(userRunnable);
//        thread.stop();
        thread.interrupt();

        // callable的执行
        // 需要将callable包装成runnable
        UserCallable userCallable = new UserCallable();
        FutureTask<String> stringFutureTask = new FutureTask<>(userCallable);
        new Thread(stringFutureTask).start();
        try {
            String callableResult = stringFutureTask.get();
            System.out.println("callable 的结果： " + callableResult);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
