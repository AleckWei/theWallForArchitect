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

    private static final String[] deviceName
            = {"空调", "风扇", "冰箱", "除湿机", "电暖器", "洗衣机",
            "ai微感器", "sos", "水浸报警器", "多联机", "智能魔方", "智能门锁"};

    private static final String[] midList =
            {"10001", "828214", "28c96", "099810", "981904", "28a02",
                    "10280", "427891", "739r98", "dkb394", "0bhe93", "940j10"};

    public void initData(final OnRequestListener<List<ItemBean>> listener) {
        final List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            ItemBean itemBean = new ItemBean();
            int index = random.nextInt(12);
            itemBean.setMid(midList[index]);
            itemBean.setDeviceName(deviceName[index]);
            itemBean.setDeviceState(1);
            itemBean.setPow(1);
            list.add(itemBean);
        }
        Random random2 = new Random();

        if (random2.nextInt(202) > 200) {
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
