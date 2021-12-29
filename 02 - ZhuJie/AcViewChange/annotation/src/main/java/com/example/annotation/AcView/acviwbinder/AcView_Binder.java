package com.example.annotation.AcView.acviwbinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WWJ
 * @detail: 由代码生成的binder类，不需手写维护。这里供debug使用
 */
public class AcView_Binder {
    public static final int PRESENTER_TYPE = 0;
    public static final int MODEL_TYPE = 1; 
    private static final Map<String, String> mAcViewPresenterMap = new HashMap<>();
    private static final Map<String, String> mAcViewModelMap = new HashMap<>(); 

    public static Map<String, String> getMap(int type) {
        switch (type) {
            case PRESENTER_TYPE:
                return getPresenterMap();
            case MODEL_TYPE:
                return getModelMap();
            default:
                return null;
        }
    }
    private static Map<String, String> getPresenterMap() {
        if (mAcViewPresenterMap.size() == 0) {
            mAcViewPresenterMap.put("10001", "com.example.acviewchange.acview.AcView10001.AcViewPresenter");
            mAcViewPresenterMap.put("28a02", "com.example.acviewchange.acview.AcView28a02.AcView28a02Presenter");
            mAcViewPresenterMap.put("28c96", "com.example.acviewchange.acview.AcView28c96.AcView28c96Presenter");
            mAcViewPresenterMap.put("828214", "com.example.acviewchange.acview.AcView828214.AcView828214Presenter");
            mAcViewPresenterMap.put("981904", "com.example.acviewchange.acview.AcView981904.AcView981904Presenter");
        }
        return mAcViewPresenterMap;
    }

    private static Map<String, String> getModelMap() {
        if (mAcViewModelMap.size() == 0) {
            mAcViewModelMap.put("10001", "com.example.acviewchange.acview.AcView10001.AcViewModel");
            mAcViewModelMap.put("28a02", "com.example.acviewchange.acview.AcView28a02.AcView28a02Model");
            mAcViewModelMap.put("828214", "com.example.acviewchange.acview.AcView828214.AcView828214Model");
            mAcViewModelMap.put("981904", "com.example.acviewchange.acview.AcView981904.AcView981904Model");
        }
        return mAcViewModelMap;
    }
}