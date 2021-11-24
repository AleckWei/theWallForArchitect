package com.example.rxjava03.application;

import android.app.Application;
import android.util.Log;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("wwj", "setErrorHandler accept Exception: \n " + throwable.toString());
            }
        });

    }
}
