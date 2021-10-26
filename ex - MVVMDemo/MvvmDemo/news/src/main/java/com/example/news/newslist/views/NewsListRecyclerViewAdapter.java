package com.example.news.newslist.views;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.base.BaseCustomVM;
import com.example.news.base.viewholder.BaseViewHolder;
import com.example.news.newslist.views.picturetitleview.PictureTitleVM;
import com.example.news.newslist.views.picturetitleview.PictureTitleView;
import com.example.news.newslist.views.titleview.TitleView;
import com.example.news.newslist.views.titleview.TitleViewVM;

import java.util.List;

public class NewsListRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private final int VIEW_TYPE_PICTURE_TITLE = 1;
    private final int VIEW_TYPE_TITLE = 2;
    private List<BaseCustomVM> mItem;

    NewsListRecyclerViewAdapter() {
    }

    public void setData(List<BaseCustomVM> itemList) {
        mItem = itemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mItem.get(position) instanceof PictureTitleVM) {
            return VIEW_TYPE_PICTURE_TITLE;
        } else if (mItem.get(position) instanceof TitleViewVM) {
            return VIEW_TYPE_TITLE;
        }
        return -1;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PICTURE_TITLE) {
            PictureTitleView pictureTitleView = new PictureTitleView(parent.getContext());
            return new BaseViewHolder(pictureTitleView);
        } else if (viewType == VIEW_TYPE_TITLE) {
            TitleView titleView = new TitleView(parent.getContext());
            return new BaseViewHolder(titleView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mItem.get(position));
    }

    @Override
    public int getItemCount() {
        if (mItem != null && mItem.size() > 0) {
            return mItem.size();
        }
        return 0;
    }
}
