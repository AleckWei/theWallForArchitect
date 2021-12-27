package com.example.acviewchange.acview;

import android.util.Log;

import com.example.acviewchange.acview.AcView10001.AcViewPresenter;
import com.example.acviewchange.acview.viewimp.IPluginView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 绑定view、presenter、model的绑定类
 */
public class AcViewBinder {

    private static final String TAG = "AcViewBinder";

    public static void bindPresenter(AcView target, String mid) {
        try {
            if (target != null) {
                Class<?> AcView_BinderCls = Class.forName("com.example.annotation.AcView.acviwbinder.AcView_Binder");
                Method getMap = AcView_BinderCls.getDeclaredMethod("getMap", int.class);
                Map<String, String> presenterMap = (Map) getMap.invoke(null, 0);
                String presenterName;
                if (presenterMap.containsKey(mid)) {
                    presenterName = presenterMap.get(mid);
                } else {
                    presenterName = presenterMap.get("10001");
                }

                Class<?> presenterClass = Class.forName(presenterName);
                Constructor<?> constructor = presenterClass.getConstructor(IPluginView.class, String.class);
                AcViewBasePresenter presenter = (AcViewBasePresenter) constructor.newInstance(target, mid);
                target.mPresenter = presenter;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "presenter Binding Failed");
        }
    }

    public static void bindModel(AcViewBasePresenter target, String mid) {
        try {
            if (target != null) {
                Class<?> AcView_BinderCls = Class.forName("com.example.annotation.AcView.acviwbinder.AcView_Binder");
                Method getMap = AcView_BinderCls.getDeclaredMethod("getMap", int.class);
                Map<String, String> presenterMap = (Map) getMap.invoke(null, 1);
                String presenterName;
                if (presenterMap.containsKey(mid)) {
                    presenterName = presenterMap.get(mid);
                } else {
                    presenterName = presenterMap.get("10001");
                }
                Class<?> presenterClass = Class.forName(presenterName);
                Constructor<?> constructor = presenterClass.getConstructor(String.class);
                AcViewBaseModel model = (AcViewBaseModel) constructor.newInstance(mid);
                target.model = model;
            }
        } catch (Exception e) {
            Log.d(TAG, "model Binding Failed");
            e.printStackTrace();
        }
    }
}
