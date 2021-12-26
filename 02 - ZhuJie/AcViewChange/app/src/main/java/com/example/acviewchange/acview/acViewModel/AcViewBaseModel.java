package com.example.acviewchange.acview.acViewModel;

import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.annotation.AcView.annotation.AcViewModelAnnotation;

import java.util.Random;

@AcViewModelAnnotation
public abstract class AcViewBaseModel {

    protected final Random random;

    protected int pow;

    protected String mid;

    public AcViewBaseModel() {
        this("10001");
    }

    public AcViewBaseModel(String mid) {
        random = new Random();
         pow = 0;
        this.mid = mid;
    }

    /**
     * 发送开关机指令
     *
     * @param listener 用于指令控制回调
     */
    protected abstract void sendPowCmd(final OnRequestListener<Integer> listener);

}
