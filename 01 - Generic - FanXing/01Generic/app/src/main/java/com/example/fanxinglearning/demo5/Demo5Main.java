package com.example.fanxinglearning.demo5;

import android.util.Log;

public class Demo5Main {
    private static final String TAG = "泛型";
    private static final String TAG_DEMO5 = TAG + "demo5";

    public static void main() {
        Apple apple = new Apple();
        String appleKey = apple.getKey();
        Log.v(TAG_DEMO5, "apple 的 key 为：" + appleKey);

        Pair<String, Integer> pair = new Pair<>("我的家", 100);
        String pairKey = pair.getKey();
        Integer pairVal = pair.getVal();
        Log.v(TAG_DEMO5, "pairKey: " + pairKey + " pairVal: " + pairVal);

    }

}
