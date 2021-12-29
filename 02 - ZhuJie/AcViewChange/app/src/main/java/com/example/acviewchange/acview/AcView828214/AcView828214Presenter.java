package com.example.acviewchange.acview.AcView828214;

import android.os.Build;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.acviewchange.acview.AcViewBasePresenter;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;
import com.example.common.widget.WholeScreenLoadingViewV3;

@AcViewPresenterAnnotation("828214")
public class AcView828214Presenter extends AcViewBasePresenter<String> {

    WholeScreenLoadingViewV3 view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AcView828214Presenter(@NonNull IPluginView iPluginView, @NonNull String mid) {
        super(iPluginView, mid);
        view = new WholeScreenLoadingViewV3(iPluginView.getContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void sendPowCmd() {
        Log.d("wwj", "828214的pre");
        view.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.dismiss();
            }
        }, 2000);
    }
}
