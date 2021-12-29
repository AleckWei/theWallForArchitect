package com.example.acviewchange.acview.AcView28c96;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.AcViewBasePresenter;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;
import com.example.common.widget.WholeScreenLoadingViewV3;

@AcViewPresenterAnnotation("28c96")
public class AcView28c96Presenter extends AcViewBasePresenter<Integer> {

    WholeScreenLoadingViewV3 view;

    public AcView28c96Presenter(@NonNull IPluginView iPluginView, @NonNull String mid) {
        super(iPluginView, mid);
        view = new WholeScreenLoadingViewV3(iPluginView.getContext());
    }

    @Override
    public void sendPowCmd() {
        Log.d("wwj", "28c96的pre");
        view.show();
    }
}
