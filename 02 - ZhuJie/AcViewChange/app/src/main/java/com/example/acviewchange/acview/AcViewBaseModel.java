package com.example.acviewchange.acview;

import com.example.acviewchange.dataimp.OnRequestListener;

import java.util.Random;

public abstract class AcViewBaseModel<T> {

    protected final Random random;

    protected int pow;

    protected String mid;

    AcViewBaseModel() {
        this("10001");
    }

    public AcViewBaseModel(String mid) {
        random = new Random();
        pow = 1;
        this.mid = mid;
    }

    /**
     * 发送开关机指令
     *
     * @param listener 用于指令控制回调
     */
    public abstract void sendPowCmd(final OnRequestListener<T> listener);

}
