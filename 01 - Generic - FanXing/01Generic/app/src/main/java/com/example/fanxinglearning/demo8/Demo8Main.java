package com.example.fanxinglearning.demo8;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;

import java.util.ArrayList;

/**
 * demo8 类型通配符的上限 / 下限
 */
public class Demo8Main {
    private static String TAG = MainActivity.TAG;
    private static String TAG_DEMO8 = TAG + "demo8";

    public static void main() {
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Cat> cats = new ArrayList<>();
        ArrayList<MiniCat> miniCats = new ArrayList<>();

//        cats.addAll(animals); // addAll也是用的上限通配符，故不可添加父类的元素
        cats.addAll(miniCats); // 但是可以添加自身以及自身的子类
//        showAnimal(animals); // 会发现这里报错了
        showAnimal(cats);
        showAnimal(miniCats);

        showAnimalV2(animals);
        showAnimalV2(cats);
//        showAnimalV2(miniCats);// 会发现这里报错了

    }

    /**
     * 泛型的上限通配符
     * 传递的泛型类型，只能是Cat或是Cat的子类类型
     * 需要注意的是：上限通配符，是不允许添加元素的
     *
     * @param list
     */
    public static void showAnimal(ArrayList<? extends Cat> list) {
        // 上限通配符实际上是不允许添加任何元素的
//        list.add(new Animal());
//        list.add(new Cat());
//        list.add(new MiniCat());

        for (int i = 0; i < list.size(); i++) {
            Cat cat = list.get(i);
            Log.v(TAG_DEMO8, cat.toString());
        }
    }

    /**
     * 类型通配符下限，要求集合只能是Cat或者是Cat的父类类型
     * 下限通配符是可以添加元素的，但是不能实现数据的约束
     *
     * @param list
     */
    public static void showAnimalV2(ArrayList<? super Cat> list) {
        list.add(new Cat());
        list.add(new MiniCat());
        for (Object o : list) {
            // 这里只能使用Object接收
            Log.v(TAG_DEMO8, o.toString());
        }
    }
}
