package com.example.acviewchange.acview;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.viewimp.IPluginView;

public abstract class AcViewBasePresenter<T> {

    protected final String mid;
    protected IPluginView iView;
    public AcViewBaseModel<T> model;

    AcViewBasePresenter() {
        this.mid = "10001";
    }

    public AcViewBasePresenter(@NonNull IPluginView iPluginView,
                               @NonNull String mid) {
        this.mid = mid;
        iView = iPluginView;
        AcViewBinder.bindModel(this, mid);
    }

    public abstract void sendPowCmd();

}
