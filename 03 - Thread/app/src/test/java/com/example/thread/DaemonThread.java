package com.example.thread;

import org.junit.Test;

public class DaemonThread {

    private static class UserThread extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!isInterrupted()) {
                System.out.println(name + " is running");
            }
            System.out.println(name + " isInterrupt flag is: " +
                    Thread.currentThread().isInterrupted());
        }
    }

    @Test
    public void main() throws InterruptedException {
        UserThread userThread = new UserThread();
        // 设置为守护进程，一定要执行在start之前
        userThread.setDaemon(true);
        userThread.start();
        Thread.sleep(1000);
        userThread.interrupt();
    }

}
