package com.example.zhujie.reflection;

import android.util.Log;

/**
 * 讲解类是如何加载的
 */
public class Test05 {

    /**
     * 类加载的 顺序 总结
     * 1.加载到内存（方法区），会产生一个对应的java.lang.Class对象（堆）
     * 2.链接，链接（1.确保代码安全；2.为变量分配内存，基于初始值；3.符号引用替换为直接引用）结束后
     * m = 0 （变量被初始化了）
     * 3.初始化
     *  <clinit>() {
     *      把静态代码块合并（比如其中对相同对象的赋值操作按书写顺序执行赋值，实现合并）
     *  }
     */

    public static void main() {
        A a = new A();
        Log.v("wwj", "main方法" + A.m);
    }
}

class A {
    static {
        Log.v("wwj", "A类静态代码块初始化");
        m = 300;
    }

    static int m = 100;

    // 上面2处static代码块是有执行的先后次序的，
    // 按照这个写法，m先会被赋值300，然后被赋值100
    // 所以最后拿到的m的值实际上是100

    public A() {
        Log.v("wwj", "A类的无参构造器");
    }
}
