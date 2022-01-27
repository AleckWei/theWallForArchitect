package com.example.thread;

import com.example.utils.SleepTools;

import org.junit.Test;

/**
 * 演示 volatile 关键字无法保证线程安全
 */
public class VolatileUnSafe {

    private static class VolatileVar implements Runnable {

        private volatile int a = 0;

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            a += 2;
            System.out.println(name + " a = " + a);
            SleepTools.ms(100);
            a++;
            System.out.println(name + " a = " + a);
        }
    }

    @Test
    public void main() {
        VolatileVar volatileVar = new VolatileVar();
        Thread t1 = new Thread(volatileVar);
        Thread t2 = new Thread(volatileVar);
        Thread t3 = new Thread(volatileVar);
        Thread t4 = new Thread(volatileVar);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
