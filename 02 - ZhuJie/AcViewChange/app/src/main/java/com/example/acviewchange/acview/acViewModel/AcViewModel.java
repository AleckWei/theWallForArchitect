package com.example.acviewchange.acview.acViewModel;

import android.os.Handler;

import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.annotation.AcView.annotation.AcViewModelAnnotation;

@AcViewModelAnnotation
public class AcViewModel extends AcViewBaseModel {

    public AcViewModel() {
        super();
    }

    public AcViewModel(String mid) {
        super(mid);
    }

    @Override
    public void sendPowCmd(final OnRequestListener<Integer> listener) {
        if (random.nextInt(10) <= 8) {
            pow = pow == 1 ? 0 : 1;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.onOk(pow);
                }
            }, 500);
        } else {
            listener.onFail(new Throwable("Network Error"));
        }
    }

}
