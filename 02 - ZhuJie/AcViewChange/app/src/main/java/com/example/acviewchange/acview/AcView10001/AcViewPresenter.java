package com.example.acviewchange.acview.AcView10001;

import android.util.Log;

import com.example.acviewchange.acview.AcViewBasePresenter;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;

@AcViewPresenterAnnotation
public class AcViewPresenter extends AcViewBasePresenter<Integer> {

    public AcViewPresenter(IPluginView iPluginView, String mid) {
        super(iPluginView, mid);
    }

    public void sendPowCmd() {
        Log.d("wwj", "通用的pre");
        iView.setLoading(true);
        model.sendPowCmd(new OnRequestListener<Integer>() {
            @Override
            public void onOk(Integer result) {
                iView.setLoading(false);
                if (result == 0) {
                    // 关机
                    iView.showPowOffView();
                } else {
                    // 开机
                    iView.showPowOnView();
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                iView.setLoading(false);
                Log.d("wwj", "发送失败：" + throwable.getMessage());
            }
        });
    }

}
