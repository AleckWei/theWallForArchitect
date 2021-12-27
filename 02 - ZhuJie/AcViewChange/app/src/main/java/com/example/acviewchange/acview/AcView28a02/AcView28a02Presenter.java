package com.example.acviewchange.acview.AcView28a02;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.AcViewBasePresenter;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;

/**
 * @author WWJ
 * @detail:
 * @date: 2021/12/27 16:44
 */
@AcViewPresenterAnnotation("28a02")
public class AcView28a02Presenter extends AcViewBasePresenter<Integer> {

    public AcView28a02Presenter(@NonNull IPluginView iPluginView, @NonNull String mid) {
        super(iPluginView, mid);
    }

    @Override
    public void sendPowCmd() {
        iView.setLoading(true);
        model.sendPowCmd(new OnRequestListener<Integer>() {
            @Override
            public void onOk(Integer result) {
                iView.setLoading(false);
                if (result == 0) {
                    iView.showPowOffView();
                } else {
                    iView.showPowOnView();
                }
            }

            @Override
            public void onFail(Throwable throwable) {
                iView.setLoading(false);
                iView.showWrongTips(throwable.getMessage());
            }
        });
    }
}
