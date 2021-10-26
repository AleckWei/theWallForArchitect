package com.example.news.newslist.views.picturetitleview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.base.imp.ICustomView;
import com.example.news.databinding.PicTitleViewBinding;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PictureTitleView extends LinearLayout implements ICustomView<PictureTitleVM> {
    private static final String TAG = "CustomNewsPicTitleView";

    private PicTitleViewBinding mBinding;
    private PictureTitleVM mViewModel;

    public PictureTitleView(Context context) {
        super(context);
        init();
    }

    public PictureTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext())
                    .load(url)
                    .transition(withCrossFade())
                    .into(view);
        }
    }

    public void init() {
        // 用inflater来替代实现onLayout（偷懒的写法）
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.pic_title_view, this, false);
        mBinding.getRoot().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewModel != null && !TextUtils.isEmpty(mViewModel.link)) {
                    // TODO: 2021/10/10 写一个webViewActivity去开启新的webView
                    Log.v(TAG, "打开webView页面");
                }
            }
        });
        // 最后把这个这个自定义View加入进LinearLayout当中
        addView(mBinding.getRoot());
    }

    @Override
    public void setData(PictureTitleVM data) {
        mBinding.setPicViewModel(data);
        mBinding.executePendingBindings();
        mViewModel = data;
    }
}
