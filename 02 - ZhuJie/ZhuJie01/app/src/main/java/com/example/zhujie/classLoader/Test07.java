package com.example.zhujie.classLoader;

import android.util.Log;

/**
 * 获取类加载器
 */
public class Test07 {

    public static void main() {
        // 获取系统类的加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Log.d("wwj", "系统的类加载器： " + systemClassLoader);
        // 输出 dalvik.system.PathClassLoader

        // 获取系统类加载器的父类加载器（也就是扩展加载器）
        ClassLoader parent = systemClassLoader.getParent();
        Log.d("wwj", "系统的类加载器的父类： " + parent);
        // 输出 java.lang.BootClassLoader

        // 获取扩展加载器的父类加载器（也就是根加载器 C/C++ 编写的，无法直接获取）
        ClassLoader parent1 = parent.getParent();
        Log.d("wwj", "扩展类加载器的父类： " + parent1);
        // 输出 null

        // android 的 jvm 是基于原始的java虚拟机进行封装的
        // 所以和java本身的ClassLoader输出还是有区别的

        // 测试当前类是哪个加载器加载的
        try {
            ClassLoader classLoader = Class.forName("com.example.zhujie.classLoader.Test07").getClassLoader();
            Log.d("wwj", "Test07的ClassLoader：" + classLoader);
            // 输出dalvik.system.PathClassLoader，也即是系统的类加载器

            // 测试封装好的jdk的内部类的类加载器
            classLoader = Class.forName("java.lang.Object").getClassLoader();
            Log.d("wwj", "Object的ClassLoader：" + classLoader);
            // 输出 java.lang.BootClassLoader,即扩展类加载器来加载的
            // 这里和java的JVM也是有区别的

            // 如何获取系统类加载器可以加载的路径
            String property = System.getProperty("java.class.path");
            Log.d("wwj", "可以加载的路径： \n" + property);
            // 输出 . ??????

            // 双亲委派机制
            // 即下层类加载器会去上层去找是否加载过 包.类 ,如果上层有加载过，就使用上层的
            // 保证代码的安全性。因为尽管同包同名，但高级的类加载器加载过正确的类，就不会加载这个自定义的了
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
