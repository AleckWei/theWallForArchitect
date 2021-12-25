package com.example.zhujieapt;

import android.app.Activity;

public class ButterKnife {
    public static void bind(Activity target) {
        String viewBindingName = target.getClass().getName() + "_ViewBinding";
        Class<?> activityClass = null;
        try {
            activityClass = Class.forName(viewBindingName);
            if (IButterKnife.class.isAssignableFrom(activityClass)) {
                IButterKnife iButterKnife = (IButterKnife) activityClass.newInstance();
                iButterKnife.bind(target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
