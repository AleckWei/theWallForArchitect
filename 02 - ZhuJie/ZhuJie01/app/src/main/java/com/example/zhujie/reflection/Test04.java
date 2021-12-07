package com.example.zhujie.reflection;

import android.util.Log;

import java.lang.annotation.ElementType;

/**
 * 所有类型的Class对象
 */
public class Test04 {

    public static void main() {
        // 类的class对象
        Class c1 = Object.class;
        // 接口的class对象
        Class c2 = Comparable.class;
        // 一维数组的class对象
        Class c3 = String[].class;
        // 二维数组的class对象
        Class c4 = int[][].class;
        // 注解的class对象
        Class c5 = Override.class;
        // 枚举类型的class对象
        Class c6 = ElementType.class;
        // 基本数据类型的class对象
        Class c7 = Integer.class;
        // void的class对象
        Class c8 = void.class;
        // Class本身的class对象
        Class c9 = Class.class;

        Log.d("wwj", "" + c1);
        Log.d("wwj", "" + c2);
        Log.d("wwj", "" + c3);
        Log.d("wwj", "" + c4);
        Log.d("wwj", "" + c5);
        Log.d("wwj", "" + c6);
        Log.d("wwj", "" + c7);
        Log.d("wwj", "" + c8);
        Log.d("wwj", "" + c9);

        int[] a = new int[10];
        int[] b = new int[100];
        Log.d("wwj", a.getClass().hashCode() + "");
        Log.d("wwj", b.getClass().hashCode() + "");
        // 只要元素类型和数组维度一样，它们的Class对象就是一样的
    }

}
