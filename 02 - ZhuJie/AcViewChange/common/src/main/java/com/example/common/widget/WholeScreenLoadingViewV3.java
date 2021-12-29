package com.example.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.common.R;

public class WholeScreenLoadingViewV3 {

    Context mContext;
    View rootView;
    WindowManager wm;
    WindowManager.LayoutParams params;
    boolean isAdd = false;

    public WholeScreenLoadingViewV3(Context context) {
        mContext = context;
        rootView = LayoutInflater.from(context)
                .inflate(R.layout.whole_loading_view, null, false);
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            params = new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.LAST_APPLICATION_WINDOW,
                    WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
                    PixelFormat.RGBA_8888);
            ((Activity) context).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            params = new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.LAST_APPLICATION_WINDOW,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    PixelFormat.RGBA_8888);
        }

        TextView tvBack = rootView.findViewById(R.id.tv_click_back);
        tvBack.setOnClickListener(v -> dismiss());

        rootView.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                dismiss();
                return true;
            }
            return false;
        });
    }

    public void show() {
        if (rootView != null) {
            rootView.setFocusable(true);
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            if (!isAdd) {
                wm.addView(rootView, params);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ((Activity) mContext).getWindow().setStatusBarColor(mContext.getResources().getColor(R.color.loading));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        ((Activity) mContext).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }
                }
                isAdd = true;
            }
        }


    }

    public void dismiss() {
        if (isAdd) {
            if (rootView != null) {
                wm.removeViewImmediate(rootView);
                rootView.clearFocus();
            }
//            rootView = null;
            isAdd = false;
        }
    }


}
