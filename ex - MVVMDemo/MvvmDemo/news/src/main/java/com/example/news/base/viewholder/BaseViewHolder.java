package com.example.news.base.viewholder;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.news.base.BaseCustomVM;
import com.example.news.base.imp.ICustomView;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    ICustomView view;

    public BaseViewHolder(ICustomView itemView) {
        super((View) itemView);
        this.view = itemView;
    }

    public void bind(BaseCustomVM itemVm) {
        view.setData(itemVm);
    }
}
