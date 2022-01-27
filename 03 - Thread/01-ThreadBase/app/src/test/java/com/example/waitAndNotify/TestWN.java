package com.example.waitAndNotify;

import org.junit.Test;

/**
 * 测试wait
 */
public class TestWN {
    private static final Express express = new Express(0, Express.CITY);

    @Test
    public void main() {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 快递地点变化
        express.changeKm();
    }

    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waiteSite();
        }
    }

}
