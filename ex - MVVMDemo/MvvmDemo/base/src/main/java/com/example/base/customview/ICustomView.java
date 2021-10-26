package com.example.base.customview;

public interface ICustomView<viewMode extends BaseCustomVM> {
    void setData(viewMode vm);

    void setStyle(int resId);

    void setActionListener(ICustomViewActionListener listener);
}
