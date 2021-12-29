package com.example.acviewchange.acview.AcView981904;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.AcViewBasePresenter;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;
import com.example.common.widget.WholeScreenLoadingViewV3;

@AcViewPresenterAnnotation("981904")
public class AcView981904Presenter extends AcViewBasePresenter<String> {

    WholeScreenLoadingViewV3 view;

    public AcView981904Presenter(@NonNull IPluginView iPluginView, @NonNull String mid) {
        super(iPluginView, mid);
        view = new WholeScreenLoadingViewV3(iPluginView.getContext());
    }

    @Override
    public void sendPowCmd() {
        Log.d("wwj", "981904的pre");
        if (view != null) {
            view.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.dismiss();
                }
            }, 2000);
        }
    }
}
