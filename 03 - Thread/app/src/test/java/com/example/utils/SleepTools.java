package com.example.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public class SleepTools {

    /**
     * 按照秒数进行休眠
     *
     * @param seconds 休眠的秒数
     */
    public static void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("休眠second被捕捉异常");
            e.printStackTrace();
        }
    }

    /**
     * 按照毫秒进行休眠
     *
     * @param seconds 休眠的毫秒数
     */
    public static void ms(int seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
