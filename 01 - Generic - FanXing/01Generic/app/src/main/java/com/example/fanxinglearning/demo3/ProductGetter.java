package com.example.fanxinglearning.demo3;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * 抽奖的类
 *
 * @param <T> 各种抽奖的奖品的类型
 */
public class ProductGetter<T> {

    private Random random = new Random();

    private T product; // 奖品

    ArrayList<T> list = new ArrayList<>(); // 奖品池

    // 对外暴露的添加方法
    public void addProducts(T t) {
        list.add(t);
    }

    // 对外暴露的获取方法
    public T getProduct() {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    /**
     * 定义了一个泛型方法
     *
     * @param list 参数
     * @param <E>  泛型标识，具体类型由调用方法时来指定
     * @return 随机下标的list当中的item
     */
    public <E> E getProduct(ArrayList<E> list) {
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    /**
     * 静态的泛型方法，采用多个泛型类型
     */
    public static <T, E, K> void printType(T t, E e, K k) {
        Log.v("demo6", t + "\t" + t.getClass().getSimpleName());
        Log.v("demo6", e + "\t" + e.getClass().getSimpleName());
        Log.v("demo6", k + "\t" + k.getClass().getSimpleName());
    }

    /**
     * 泛型可变参数的定义
     */
    public static <E> void myPrint(E... e) {
        for (int i = 0; i < e.length; i++) {
            Log.v("demo6", String.valueOf(e[i]));
        }
    }
}
