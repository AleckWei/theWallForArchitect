package com.example.acviewchange.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acviewchange.R;
import com.example.acviewchange.acview.databean.ItemBean;
import com.example.acviewchange.recyclerview.viewholder.HomeItemViewHolder;
import com.example.acviewchange.viewimp.UpdateViewListener;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeItemViewHolder> {

    private final List<ItemBean> mData;
    private final UpdateViewListener mListener;

    public HomeAdapter(List<ItemBean> list, UpdateViewListener listener) {
        mData = list;
        mListener = listener;
    }

    @NonNull
    @Override
    public HomeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new HomeItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemViewHolder holder, int position) {
//        TextView tvBgContent = holder.itemView.findViewById(R.id.tv_bg_content);
//        tvBgContent.setText(mData.get(position).getContent());
        mListener.convert(holder.itemView, mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mData != null && mData.size() > 0) {
            return mData.size();
        }
        return 0;
    }
}
