package com.example.thread;

import org.junit.Test;

public class SafetyStop {

    private static class UserThread extends Thread {
        public UserThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            // 这个写法，这个线程是可以被中断的
//            while (!isInterrupted()) {
            // 但是这个写法，这个线程就永远不会被中断了
            while (true) {
                System.out.println(name + " is running");
            }
//            System.out.println(name + " interrupt flag is: " + isInterrupted());
        }
    }

    private static class UserThreadWhitException extends Thread {
        public UserThreadWhitException(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!isInterrupted()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(name + " isInterrupt flag is: " +
                            Thread.currentThread().isInterrupted());
//                    interrupt();
                    e.printStackTrace();
                }
                System.out.println("while 当中： " + name);
            }
            System.out.println(name + " isInterrupt flag is: " +
                    Thread.currentThread().isInterrupted());
        }
    }

    private static class UserRunnable implements Runnable {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(name + " runnableThread is running");
            }
            System.out.println(name + " isInterrupt flag is: " + Thread.currentThread().isInterrupted());
        }
    }

    @Test
    public void main() throws InterruptedException {
//        UserThread userThread = new UserThread("user");

//        UserRunnable userRunnable = new UserRunnable();
//        Thread userThread = new Thread(userRunnable);

        UserThreadWhitException userThread = new UserThreadWhitException("exceptionName");

        userThread.start();
        Thread.sleep(20);
        userThread.interrupt();


    }


}
