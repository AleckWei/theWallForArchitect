package com.example.fanxinglearning.demo8_1;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;
import com.example.fanxinglearning.demo8.Cat;

import java.util.TreeSet;

/**
 * 下限通配符的使用
 */
public class Demo8_1Main {

    private static String TAG = MainActivity.TAG;
    private static String TAG_DEMO8_1 = TAG + "demo8_1";

    public static void main() {
//        TreeSet<Cat> treeSet = new TreeSet<>(new ComparatorByCatAge());
        TreeSet<Cat> treeSet = new TreeSet<>(new ComparatorByName());

        // 下限通配符是不可以用子类的集合去实例的
//        TreeSet<Cat> treeSet = new TreeSet<>(new ComparatorByMiniCatLevel()); // 可以发现这里报错了

        treeSet.add(new Cat("jerry", 20));
        treeSet.add(new Cat("amy", 22));
        treeSet.add(new Cat("frank", 35));
        treeSet.add(new Cat("jim", 15));
        for (Cat cat : treeSet) {
            Log.v(TAG_DEMO8_1, cat.toString());
        }
    }
}

