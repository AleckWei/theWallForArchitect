package com.example.fanxinglearning.demo7;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;

/**
 * 类型通配符
 */
public class Demo7Main {

    public static String TAG = MainActivity.TAG;
    public static String TAG_DEMO7 = TAG + "demo7";

    public static void main() {
        Box<Number> box1 = new Box<>();
        box1.setFirst(1000);
        showBox(box1);

        Box<Integer> box2 = new Box<>();
        box2.setFirst(200);
        showBox(box2);
    }

    // 当不确定传入的实参是什么的时候，采用？的接收方法，表示接收任意Object
//    public static void showBox(Box<?> box) {
//        Object first = box.getFirst();
//        Log.v(TAG_DEMO7, String.valueOf(first));
//    }

    // 类型通配符的上限
    public static void showBox(Box<? extends Number> box) {
        Number first = box.getFirst();
        Log.v(TAG_DEMO7, String.valueOf(first));
    }

//    public static void showBox(Box<Integer> box) {
//        Number first = box.getFirst();
//        Log.v(TAG_DEMO7, String.valueOf(first));
//    }
}
