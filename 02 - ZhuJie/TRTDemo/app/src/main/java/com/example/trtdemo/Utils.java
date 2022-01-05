package com.example.trtdemo;

import android.util.Log;

public class Utils {
    private static final String TAG = "Utils";

    public static void test() {
        Log.d(TAG, "这里是Util");
        throw new IllegalStateException("啊啊啊啊，出错啦！！");
    }
}
