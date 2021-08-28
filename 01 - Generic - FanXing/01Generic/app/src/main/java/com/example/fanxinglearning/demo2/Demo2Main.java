package com.example.fanxinglearning.demo2;

import android.util.Log;

public class Demo2Main {

    private static final String TAG = "泛型";
    private static final String TAG_DEMO2 = TAG + "demo2";

    public static void main() {
        Generic<String> strGeneric = new Generic<>("wwj");
        String key = strGeneric.getKey();
        Log.d(TAG_DEMO2, "key:" + key);

        Generic<Integer> intGeneric = new Generic<>(721);
        int key1 = intGeneric.getKey();
        Log.d(TAG_DEMO2, "key1:" + key1);

        // 泛型类在创建对象时，没有指定类型的话，将作为Object的对象来处理
        Generic generic = new Generic(1 == 1);
        Object key2 = generic.getKey();
        Log.d(TAG_DEMO2, "key2:" + key2);

        // 泛型类不支持基本数据类型，只支持类类型
        // Generic<int> intGeneric2 = new Generic<>(111);

        // 同一泛型类，根据不同的数据类型创建对象时，本质上是同一类型
        Log.d(TAG_DEMO2, "" + intGeneric.getClass()); // class com.example.fanxinglearning.demo2.Generic
        Log.d(TAG_DEMO2, "" + strGeneric.getClass()); // class com.example.fanxinglearning.demo2.Generic
        // 以上二者输入的Class名称都是一样的
        Log.d(TAG_DEMO2, String.valueOf(intGeneric.getClass() == strGeneric.getClass()));

    }
}
