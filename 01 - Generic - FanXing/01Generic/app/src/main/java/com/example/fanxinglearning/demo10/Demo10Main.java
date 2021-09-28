package com.example.fanxinglearning.demo10;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 泛型与数组
 */
public class Demo10Main {

    public static final String TAG = MainActivity.TAG;
    public static final String DEMO10_TAG = TAG + "demo10";

    public static void main() {
        // 声明一个数组供泛型数组引用
        ArrayList[] list = new ArrayList[5];
        ArrayList<String>[] listArr = list;

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(100);

        list[0] = intList;
        // 会发现这里出现了类型的转换错误
//        String s = listArr[0].get(0);
//        Log.v(DEMO10_TAG, s);
        /**
         *  * java当中，是不允许直接创建带泛型的数组对象的
         *
         *  * 可以通过java.lang.reflect.Array 的 newInstance(Class<T>, int)
         *    创建T[]数组
         */

        // 正确写法
        ArrayList<String>[] listArrC = new ArrayList[5];
        ArrayList<String> strListC = new ArrayList<>();
        strListC.add("abc");
        listArrC[0] = strListC;
        String s = listArrC[0].get(0);
        Log.v(DEMO10_TAG, s);

        Log.v(DEMO10_TAG, "---------------------------------------");
        Fruit<String> fruit = new Fruit<>(String.class, 3);
        fruit.put(0, "苹果");
        fruit.put(1, "香蕉");
        fruit.put(2, "梨子");

        String fruitStr = Arrays.toString(fruit.getArray());
        Log.v(DEMO10_TAG, fruitStr);

        String fruitItem = fruit.get(2);
        Log.v(DEMO10_TAG, fruitItem);

    }
}
