package com.example.zhujie.reflection;

import android.util.Log;

/**
 * 测试类什么时候会被初始化
 */
public class Test06 {

    static {
        Log.d("wwj", "main 类被加载");
    }

    public static void main() {
        // 1.主动引用
//        Child child = new Child();

        // 2.反射引用也会主动引用
//        try {
//            Class.forName("com.example.zhujie.reflection.Child");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        // 不会产生类的引用的方法
        // 1.子类调用父类的静态方法或变量
//        Log.d("wwj", "子类获取父类的静态变量（或方法）: " + Child.b);
        // 输出结果显示，只有父类被初始化了，子类并没有

        // 2.通过数组去声明一个变量
        Child[] children = new Child[5];
        // 输出显示，只有main类被加载了
        // 这样声明一个数组，仅是声明了一个内存空间

        // 3.直接调用类中的常量
        Log.d("wwj", "直接调用类中的常量： " + Child.M);
        // 输出显示，Child类也是没有被初始化的
        // 这是因为在链接的过程中，常量就已经被加入到常量池之中
    }
}

class Parent {
    static int b = 2;

    static {
        Log.d("wwj", "父类被加载");
    }
}

class Child extends Parent {
    static {
        Log.d("wwj", "子类被加载");
        m = 300;
    }

    static int m = 100;
    static final int M = 1;
}
