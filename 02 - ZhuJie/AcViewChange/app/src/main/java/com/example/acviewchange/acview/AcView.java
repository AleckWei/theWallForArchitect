package com.example.acviewchange.acview;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.acviewchange.R;
import com.example.acviewchange.acview.databean.ItemBean;

public class AcView extends LinearLayout {

    private final String mid;

    public AcView(Context context) {
        super(context);
        initView(context);
        mid = "10001";
    }

    public AcView(View rootView, ItemBean data) {
        super(rootView.getContext());
        initView(rootView.getContext(), rootView);
        mid = data.getMid();
    }

    private void initView(Context context, View... parent) {
        View rootView = inflate(context, R.layout.ac_view, null);
        ConstraintLayout.LayoutParams lp =
                new ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        if (parent.length > 0) {
            ViewGroup.LayoutParams layoutParams = parent[0].getLayoutParams();
            lp.width = layoutParams.width;
            lp.height = layoutParams.height;
        } else {
            WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = systemService.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            int width = point.x;
            lp.width = (width - 10) / 2;
            lp.height = (int) ((width - 10) / 2 * 0.618);
        }

        rootView.setLayoutParams(lp);
        addView(rootView);
    }


}
