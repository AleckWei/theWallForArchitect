package com.example.zhujie.efficiency;


import android.util.Log;

import com.example.zhujie.reflection.UserV2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 分析性能问题
 */
public class Test10 {

    public static final long time = 1000000000;

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    // 普通方式创建
    public static void test01() {
        UserV2 person = new UserV2();
        long start = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            person.getName();
        }
        long end = System.currentTimeMillis();
        Log.d("wwj", "普通方法执行10亿次： " + (end - start) + " ms");
    }

    // 反射方式创建
    public static void test02() {
        UserV2 person = new UserV2();
        Class<? extends UserV2> c1 = person.getClass();

        Method getName = null;
        try {
            getName = c1.getDeclaredMethod("getName", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            try {
                getName.invoke(person, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        Log.d("wwj", "通过反射执行10亿次： " + (end - start) + " ms");
    }

    // 反射方式创建 关闭检查
    public static void test03() {
        UserV2 person = new UserV2();
        Class<? extends UserV2> c1 = person.getClass();

        Method getName = null;
        try {
            getName = c1.getDeclaredMethod("getName", null);
            getName.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < time; i++) {
            try {
                getName.invoke(person, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        Log.d("wwj", "关闭检查执行10亿次： " + (end - start) + " ms");
    }
}
