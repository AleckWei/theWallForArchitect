package com.example.acviewchange.model;

import android.os.Handler;

import com.example.acviewchange.acview.databean.ItemBean;
import com.example.acviewchange.dataimp.OnRequestListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 处理首页的数据交互
 * 由于不打算搭建网络模块，这里就用延时模拟网络请求
 * 数据交互的地方，不应与view层有任何联系
 */
public class MainModel {

    public void initData(final OnRequestListener<List<ItemBean>> listener) {
        final List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            ItemBean itemBean = new ItemBean();
            itemBean.setMid(String.valueOf(random.nextInt(6000) + 5000));
            itemBean.setContent(random.nextInt(1500) + "台出货");
            list.add(itemBean);
        }
        Random random2 = new Random();

        if (random2.nextInt(202) > 100) {
            listener.onFail(new Throwable("You catch a wrong number"));
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onOk(list);
                }
            }, 500);
        }

    }

}
