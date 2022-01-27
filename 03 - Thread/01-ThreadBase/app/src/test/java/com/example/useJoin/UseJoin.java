package com.example.useJoin;

import org.junit.Test;

/**
 * 演示join方法的维护
 */
public class UseJoin {

    @Test
    public void main() throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new JumpQueue(previous), String.valueOf(i));
            System.out.println(previous.getName() + " jump a queue to the thread: "
                    + thread.getName());
            thread.start();
            previous = thread;
        }
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    private static class JumpQueue implements Runnable {
        // 用于插队的线程
        private final Thread thread;

        public JumpQueue(Thread t) {
            thread = t;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminted");
        }
    }

}
