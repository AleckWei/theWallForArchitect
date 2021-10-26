package com.example.news.newslist.views.titleview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.news.R;
import com.example.news.base.imp.ICustomView;
import com.example.news.databinding.TitleViewBinding;


/**
 * 自定义的titleView
 * 继承了LinearLayout（ViewGroup的子类），需要实现onLayout
 */
public class TitleView extends LinearLayout implements ICustomView<TitleViewVM> {

    private static final String TAG = "CustomNewsTitleView";

    private TitleViewBinding mBinding;
    private TitleViewVM mViewModel;

    public TitleView(Context context) {
        super(context);
        init();
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        // 用inflater来替代实现onLayout（偷懒的写法）
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.title_view, this, false);
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
    public void setData(TitleViewVM data) {
        mBinding.setViewModel(data);
        mBinding.executePendingBindings();
        mViewModel = data;
    }
}
