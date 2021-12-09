package com.example.zhujie.getRunTimeClass;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 获取类的信息
 * 运行时类的信息
 */
public class Test08 {

    public static void main() {
        try {
            Class<?> personClass = Class.forName("com.example.zhujie.reflection.Person");

            // 获得类的名字
            String personClassName = personClass.getName();// 包名 + 类名
            Log.d("wwj", "getName: " + personClassName);

            // 获得类的简单名字
            String personClassSimpleName = personClass.getSimpleName();// 只有类名
            Log.d("wwj", "getSimpleName: " + personClassSimpleName);

            // 获得类的属性
            // 会发现下面这里啥输出都没有
            // 因为Person所有的属性都是private的
            Log.d("wwj", "=======================");
            Field[] personClassFields = personClass.getFields();
            // getFields 只能找到public属性
            for (Field personClassField : personClassFields) {
                Log.d("wwj", "person 的属性： " + personClassField);
            }

            Log.d("wwj", "=======================");
            personClassFields = personClass.getDeclaredFields();
            // getDeclaredFields 则可以获取所有的属性
            for (Field personClassField : personClassFields) {
                Log.d("wwj", "person 的属性： " + personClassField);
            }

            try {
                // 获取指定属性的值
                Field name = personClass.getField("name");
                Log.d("wwj", "getFiled - name" + name);
            } catch (NoSuchFieldException e) {
                Log.d("wwj", "getFiled - name - error: " + e.getMessage());
                e.printStackTrace();
            }

            // 区别依旧是 getDeclaredField("name") 可以获取所有的属性
            // 而 getField 只能获取public的
            try {
                // 获取指定属性的值
                Field name = personClass.getDeclaredField("name");
                Log.d("wwj", "getDeclaredField - name: " + name);
            } catch (NoSuchFieldException e) {
                Log.d("wwj", "getDeclaredField - name - error: " + e.getMessage());
                e.printStackTrace();
            }

            // 获取类的方法
            Log.d("wwj", "=======================");
            Method[] personClassMethods = personClass.getMethods();
            // 获得本类及其父类的全部public方法
            for (Method personClassMethod : personClassMethods) {
                Log.d("wwj", "方法： " + personClassMethod);
            }

            Log.d("wwj", "=======================");
            Method[] declaredMethods = personClass.getDeclaredMethods();
            // 仅获得本类的所有方法（包括private的）
            for (Method declaredMethod : declaredMethods) {
                Log.d("wwj", "declare方法： " + declaredMethod);
            }

            // 获得指定方法
            Log.d("wwj", "=======================");
            try {
                // 获取无参的方法
                Method getNameMethod = personClass.getMethod("getName", null);
                Log.d("wwj", "getName: " + getNameMethod);

                // 获取有参的方法
                Method setNameMethod = personClass.getMethod("setName", String.class);
                Log.d("wwj", "setName: " + setNameMethod);

                // 获取多个参数的方法
                Method setOther = personClass.getMethod("setOther", String.class, int.class);
                Log.d("wwj", "setOther: " + setOther);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            // 获取构造器
            Log.d("wwj", "=======================");
            Constructor<?>[] constructors = personClass.getConstructors();
            for (Constructor<?> constructor : constructors) {
                Log.d("wwj", "getConstructors: " + constructor);
            }
            Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
            for (Constructor<?> declaredConstructor : declaredConstructors) {
                Log.d("wwj", "getDeclaredCon: " + declaredConstructor);
            }

            Log.d("wwj", "=======================");
            try {
                Constructor<?> constructor = personClass.getConstructor();
                Log.d("wwj", "获取无参构造器：" + constructor);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            try {
                Constructor<?> constructor = personClass.getConstructor(String.class, String.class, int.class);
                Log.d("wwj", "获取有参构造器：" + constructor);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

            // 获取注解
            Annotation[] annotations = personClass.getAnnotations();
            for (Annotation annotation : annotations) {
                Log.d("wwj", "获取注解： " + annotation);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
