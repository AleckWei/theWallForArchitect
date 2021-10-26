package com.example.base.customview;

import android.view.View;

public interface ICustomViewActionListener<VM extends BaseCustomVM> {

    public static int ACTION_ROOT_VIEW_CLICKED = 1;

    void onAction(int clickType, View view, VM viewModel);
}
