package com.example.fanxinglearning.demo3;

import android.util.Log;

public class Demo3Main {
    private static final String TAG = "泛型";
    private static final String TAG_DEMO3 = TAG + "demo3";

    public static void main() {
        // new一个抽奖器
        ProductGetter<String> stringProductGetter = new ProductGetter<>();

        // 往抽奖器中填充奖池
        String[] strProducts = {"手机1", "手机2", "手机3", "手机4"};
        for (int i = 0; i < strProducts.length; i++) {
            stringProductGetter.addProducts(strProducts[i]);
        }

        // 抽取奖品
        String product = stringProductGetter.getProduct();
        Log.v(TAG_DEMO3, "恭喜您抽中了" + product);

        ProductGetter<Integer> jiangJinProducts = new ProductGetter<>();
        int[] jinagJin = {10000, 1000, 100, 10, 1, 0};
        for (int i = 0; i < jinagJin.length; i++) {
            jiangJinProducts.addProducts(jinagJin[i]);
        }

        int jiangjin = jiangJinProducts.getProduct();
        Log.v(TAG_DEMO3, "恭喜您抽中了" + jiangjin + "元");

    }

}
