package com.example.fanxinglearning.demo9;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;
import com.example.fanxinglearning.demo9.bridgeMethod.InfoImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Demo9Main {

    public static final String TAG = MainActivity.TAG;
    public static final String DEMO9_TAG = TAG + "demo9";

    public static void main() {
//        ArrayList<Integer> intList = new ArrayList<>();
//        ArrayList<String> strList = new ArrayList<>();
        // 编译完成后，上面两个的类型都已经被擦除掉了

//        Log.v(DEMO9_TAG, intList.getClass().getSimpleName()); // 输出 ArrayList
//        Log.v(DEMO9_TAG, strList.getClass().getSimpleName()); // 输出 ArrayList
//
//        Log.v(DEMO9_TAG, String.valueOf(intList.getClass() == strList.getClass()));
        // 实际上，在编译时，这里就已经告诉开发说这里 always true 了

        // 通过反射获取Erasure类字节码文件的class类对象
        Erasure<String> erasure = new Erasure<>();
        Class<? extends Erasure> clz = erasure.getClass();
        // 获取成员变量
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            //打印成员变量的名称和类型
            Log.v(DEMO9_TAG, "V1: -->" + declaredField.getName() + " : " + declaredField.getType().getSimpleName());
        }

        // 通过反射获取Erasure类字节码文件的class类对象
        ErasureV2<Integer> erasureV2 = new ErasureV2<>();
        Class<? extends ErasureV2> clzV2 = erasureV2.getClass();
        // 获取成员变量
        Field[] declaredFieldsV2 = clzV2.getDeclaredFields();
        for (Field declaredField : declaredFieldsV2) {
            //打印成员变量的名称和类型
            Log.v(DEMO9_TAG, "V2: -->" + declaredField.getName() + " : " + declaredField.getType().getSimpleName());
        }

        Log.v(DEMO9_TAG, "------------------------------------");
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            // 打印的是方法名，已经方法名的返回值类型
            Log.v(DEMO9_TAG, declaredMethod.getName() + " : " + declaredMethod.getReturnType().getSimpleName());
        }

        Log.v(DEMO9_TAG, "------------------------------------");
        Class<InfoImpl> infoClass = InfoImpl.class;
        Method[] infoImplMethods = infoClass.getDeclaredMethods();
        for (Method implMethod : infoImplMethods) {
            Log.v(DEMO9_TAG, implMethod.getName() + " : " + implMethod.getReturnType().getSimpleName());
        }
        // 会发现尽管只在InfoImpl当中，仅仅只写了一个相对于Info实现的Integer类型的方法，
        // 但是其中依然是存在一个Object类型的实现方法来完成对泛型接口的实现。
        // 这就叫桥接方法，是编译器替我们去做的。
    }
}
