package com.example.trtdemo;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 这里使用补丁包
    }
}
