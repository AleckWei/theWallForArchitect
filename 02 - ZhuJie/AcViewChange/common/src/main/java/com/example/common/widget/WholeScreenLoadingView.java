package com.example.common.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.common.R;

public class WholeScreenLoadingView {

    private Context mContext;
    private View rootView;
    private WindowManager windowManager;
    private WindowManager.LayoutParams params;
    private boolean isAdd = false;

    public WholeScreenLoadingView(Context context) {
        mContext = context;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        initView(context);
    }

    private void initView(Context context) {
        rootView = LayoutInflater
                .from(context)
                .inflate(R.layout.whole_loading_view, null, false);
        params = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.LAST_APPLICATION_WINDOW,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                PixelFormat.RGBA_8888);

        TextView tvBack = rootView.findViewById(R.id.tv_click_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void show() {
        if (!isAdd) {
            windowManager.addView(rootView, params);
            isAdd = true;
        }
    }

    public void dismiss() {
        if (isAdd) {
            windowManager.removeViewImmediate(rootView);
            isAdd = false;
        }
    }

}

