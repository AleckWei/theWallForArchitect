package com.example.common.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.RequiresApi;

import com.example.common.R;

public class WholeScreenLoadingViewV2 {

    Context context;
    View rootView;
    WindowManager wm;
    WindowManager.LayoutParams layoutParams;
    boolean isAdd = false;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public WholeScreenLoadingViewV2(Context context) {
        this.context = context;
        if (context instanceof Activity) {
            rootView = LayoutInflater.from(context).inflate(R.layout.whole_loading_view, null, false);
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            layoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    PixelFormat.RGBA_8888
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void show() {
        if (!isAdd) {
            Activity act = (Activity) context;
            act.getWindow().setStatusBarColor(Color.TRANSPARENT);
            act.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            int statusBarHeight = getStatusBarHeight(context);
            setSystemColor((Activity) context, statusBarHeight);
            wm.addView(rootView, layoutParams);
            isAdd = true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void dismiss() {
        if (isAdd) {
            Activity act = (Activity) context;
            act.getWindow().setStatusBarColor(Color.BLACK);
            act.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_LAYOUT_FLAGS|
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            wm.removeViewImmediate(rootView);
            decorView.removeView(statusBarView);
            isAdd = false;
        }
    }

    View statusBarView;
    ViewGroup decorView;

    private void setSystemColor(Activity act, int height) {
        decorView = (ViewGroup) act.getWindow().getDecorView();
        statusBarView = new View(act.getApplicationContext());
        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height
        );
        flp.gravity = Gravity.TOP;
        statusBarView.setLayoutParams(flp);
        statusBarView.setBackgroundColor(Color.parseColor("#FF6711FF"));
        decorView.addView(statusBarView);
    }

    private int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources resources = context.getResources();
        int resId = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resId);
        }
        return statusBarHeight;
    }

}
