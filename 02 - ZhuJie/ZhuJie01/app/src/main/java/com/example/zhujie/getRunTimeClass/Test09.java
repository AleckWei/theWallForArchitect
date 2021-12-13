package com.example.zhujie.getRunTimeClass;

import android.util.Log;

import com.example.zhujie.reflection.UserV2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射，动态地创建对象
 */
public class Test09 {

    public static void main() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        // 获得Class对象
        Class<?> c1 = Class.forName("com.example.zhujie.reflection.UserV2");

        // 构造一个对象
        // 本质上是调用Person当中的无参构造器
        UserV2 person = (UserV2) c1.newInstance();
        Log.d("wwj", "newInstance Person: " + person);

        // 通过构造器创建对象
        Constructor<?> constructor = c1.getConstructor(String.class, String.class, int.class);
        UserV2 person1 = (UserV2) constructor.newInstance("韦武浚", "007", 18);
        Log.d("wwj", "获取指定构造器创建的对象：" + person1);

        // 通过反射调用普通方法
        UserV2 person2 = (UserV2) c1.newInstance();
        Method setName = c1.getMethod("setName", String.class);
        // 第一个参数：对象，第二个参数，可变长度的，指定方法需要的参数
        setName.invoke(person2, "韦武浚2");
        Log.d("wwj", "person2的名字：" + person2.getName());

        // 通过反射操作属性
        UserV2 person3 = (UserV2) c1.newInstance();
        // 如果属性是私有的，则getField是拿不到的
        Field name = c1.getDeclaredField("name");
        // 关掉权限检查
        name.setAccessible(true);
        name.set(person3, "韦武浚3");
        Log.d("wwj", "person3的名字：" + person3.getName());

    }

}
