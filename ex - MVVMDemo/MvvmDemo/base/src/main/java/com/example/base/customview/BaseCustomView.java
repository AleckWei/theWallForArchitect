package com.example.base.customview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


public abstract class BaseCustomView<DB extends ViewDataBinding, VM extends BaseCustomVM>
        extends LinearLayout
        implements ICustomView<VM>, View.OnClickListener {

    private DB mDataBinding;
    private VM mViewModel;
    private ICustomViewActionListener mListener;

    public BaseCustomView(Context context) {
        super(context);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public View getRootView() {
        return mDataBinding.getRoot();
    }

    public void init() {
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (setViewLayoutId() != 0) {
            mDataBinding = DataBindingUtil.inflate(inflater, setViewLayoutId(), this, false);
            mDataBinding.getRoot().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onAction(ICustomViewActionListener.ACTION_ROOT_VIEW_CLICKED, view, mViewModel);
                    }
                    onRootClick(view);
                }
            });
            this.addView(mDataBinding.getRoot());
        }
    }

    @Override
    public void setData(VM vm) {
        mViewModel = vm;
        setDataToView(mViewModel);
        if (mDataBinding != null) {
            mDataBinding.executePendingBindings();
        }
        onDataUpdated();
    }

    protected void onDataUpdated() {
    }

    @Override
    public void setStyle(int resId) {
    }

    @Override
    public void setActionListener(ICustomViewActionListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {

    }

    protected DB getDataBinding() {
        return mDataBinding;
    }

    protected VM getViewModer() {
        return mViewModel;
    }

    // 这里都是交由子类去实现
    protected abstract int setViewLayoutId();

    protected abstract void setDataToView(VM viewModel);

    protected abstract void onRootClick(View view);

}
