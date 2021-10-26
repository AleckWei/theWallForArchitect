package com.example.base.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base.customview.BaseCustomVM;
import com.example.base.customview.ICustomView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    // 自定义组件的对象
    ICustomView view;

    public BaseViewHolder(ICustomView itemView) {
        super((View) itemView);
        view = itemView;
    }

    public void bindData(@NonNull BaseCustomVM baseCustomVM) {
        view.setData(baseCustomVM);
    }
}
