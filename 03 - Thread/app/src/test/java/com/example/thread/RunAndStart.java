package com.example.thread;

import org.junit.Test;

public class RunAndStart {

    private static class ThreadRun extends Thread {
        @Override
        public void run() {
            int i = 90;
            while (i > 0) {
                try {
                    Thread.sleep(1000);
                    System.out.println("I am " + Thread.currentThread().getName() +
                            " and now the i is " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        }
    }

    @Test
    public void main() {
        ThreadRun threadRun = new ThreadRun();
        threadRun.setName("BeCalled");
        threadRun.setPriority(6);
        threadRun.run();
    }

}
