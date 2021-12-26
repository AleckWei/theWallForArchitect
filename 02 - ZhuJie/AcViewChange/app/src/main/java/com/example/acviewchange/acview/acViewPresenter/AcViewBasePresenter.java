package com.example.acviewchange.acview.acViewPresenter;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.acViewModel.AcViewModel;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.annotation.AcView.AcViewPresenterBinder;
import com.example.annotation.AcView.annotation.AcViewPresenterAnnotation;

@AcViewPresenterAnnotation
public abstract class AcViewBasePresenter {

    protected final String mid;
    protected IPluginView iView;
    AcViewModel model;

    public AcViewBasePresenter() {
        mid = "10001";
    }

    public AcViewBasePresenter(@NonNull IPluginView iPluginView,
                               @NonNull String mid) {
        this.mid = mid;
        iView = iPluginView;
        AcViewPresenterBinder.bind(this);
        model = new AcViewModel(mid);
    }

    protected abstract void sendPowCmd();

}
