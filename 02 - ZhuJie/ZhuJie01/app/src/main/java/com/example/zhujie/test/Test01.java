package com.example.zhujie.test;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试学习什么是注解
 */
public class Test01 {

    // Override 重写的注解
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    // 表示已经过时了，不建议使用
    @Deprecated
    public static void test() {
        Log.d("wwj", "Deprecated");
    }

    @SuppressWarnings("all")
    public void test1() {
        // 如果没有上面这个“镇压警告”，这个list就会被标黄
        List list = new ArrayList<>();
    }

    public static void main(String[] args) {
        // 会发现如有@Deprecated修饰时
        // 使用该方法时会多一个杠
        test();
    }

}
