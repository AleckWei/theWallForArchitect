package com.example.fanxinglearning.demo11;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;

import java.lang.reflect.Constructor;

/**
 * 泛型与反射
 */
public class Demo11Main {

    public static final String TAG = MainActivity.TAG;
    public static final String DEMO11_TAG = TAG + "demo11";

    public static void main() {
        try {
            Class<Person> personClass = Person.class;
            Constructor<Person> constructor = personClass.getConstructor();
            Person person = constructor.newInstance();

            // 这里如果不指定泛型的话，最后new出来的对象是一个obj
            // 而不是person对象
            Class personClass1 = Person.class;
            Constructor constructor1 = personClass1.getConstructor();
            Object obj = constructor1.newInstance();
        } catch (Exception e) {
            Log.e(DEMO11_TAG, "创建异常");
        }

    }

}
