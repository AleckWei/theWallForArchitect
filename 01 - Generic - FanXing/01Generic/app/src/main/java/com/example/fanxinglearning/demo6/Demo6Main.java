package com.example.fanxinglearning.demo6;

import android.util.Log;

import com.example.fanxinglearning.MainActivity;
import com.example.fanxinglearning.demo3.ProductGetter;

import java.util.ArrayList;

/**
 * 泛型方法和使用了泛型成员的成员方法的比较
 * 泛型方法：可以在使用时动态指定数据的类型
 * 使用了泛型成员的成员方法：在类被指定具体类型生成以后，该方法就已经被指定为某一具体类型的方法了
 */
public class Demo6Main {

    private static final String TAG = MainActivity.TAG;
    private static final String TAG_DEMO6 = TAG + "demo6";

    public static void main() {
        ProductGetter<Integer> productGetter = new ProductGetter<>();
        ArrayList<String> strList = new ArrayList<>();
        strList.add("电脑1");
        strList.add("电脑2");
        strList.add("电脑3");
        strList.add("电脑4");
        strList.add("电脑5");
        // 泛型方法的调用，其类型是通用调用方法的时候来指定的
        String strProduct = productGetter.getProduct(strList);
        Log.v(TAG + TAG_DEMO6, "泛型方法 抽中奖品：" + strProduct + "\t" + strProduct.getClass().getSimpleName());

        ArrayList<Integer> intList = new ArrayList<>();
        intList.add(10000);
        intList.add(1000);
        intList.add(100);
        intList.add(10);
        intList.add(1);
        Integer jiangjinProduct = productGetter.getProduct(intList);
        Log.v(TAG + TAG_DEMO6, "泛型方法 抽中奖金：" + jiangjinProduct + "\t" + jiangjinProduct.getClass().getSimpleName());

        // 成员方法的使用：
        int[] jiangjinProducts2 = {100, 200, 300, 400};
        for (int i = 0; i < jiangjinProducts2.length; i++) {
            productGetter.addProducts(jiangjinProducts2[i]);
        }
        Integer productInMemberFunction = productGetter.getProduct();
        Log.v(TAG + TAG_DEMO6, "成员方法 抽中奖金：" + productInMemberFunction + "\t" + productInMemberFunction.getClass().getSimpleName());

        // 调用多个泛型类型的静态泛型方法
        ProductGetter.printType(100, "java", false);

        ProductGetter.myPrint(1, "2", true, 4, 5.0, 11111111);
    }
}
