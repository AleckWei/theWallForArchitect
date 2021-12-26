package com.example.acviewchange.presenter;

import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.AcView;
import com.example.acviewchange.acview.databean.ItemBean;
import com.example.acviewchange.acview.viewimp.IPluginView;
import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.acviewchange.model.MainModel;
import com.example.acviewchange.recyclerview.adapter.HomeAdapter;
import com.example.acviewchange.viewimp.IMainView;
import com.example.acviewchange.viewimp.UpdateViewListener;

import java.util.List;

/**
 * 处理主页逻辑的 presenter
 */
public class MainPresenter {

    private final IMainView iView;
    private final MainModel model;
    private final UpdateViewListener mListener
            = new UpdateViewListener() {
        @Override
        public void convert(@NonNull View rootView, @NonNull ItemBean itemData) {
            boolean isAdd = false;
            IPluginView viewItem = null;
            for (int i = 0; i < ((FrameLayout) rootView).getChildCount(); i++) {
                if (((FrameLayout) rootView).getChildAt(i) instanceof AcView) {
                    viewItem = (IPluginView) ((FrameLayout) rootView).getChildAt(i);
                    isAdd = true;
                    break;
                }
            }
            if (!isAdd) {
                AcView acView = new AcView(rootView, itemData);
                viewItem = acView;
                ((FrameLayout) rootView).addView(acView);
            }
            viewItem.setState(itemData);
        }
    };

    private HomeAdapter mAdapter;

    public MainPresenter(IMainView view) {
        iView = view;
        model = new MainModel();
    }

    public HomeAdapter getAdapter() {
        return mAdapter;
    }

    public void initAdapter(final OnRequestListener<String> listener) {
        model.initData(new OnRequestListener<List<ItemBean>>() {
            @Override
            public void onOk(List<ItemBean> result) {
                mAdapter = new HomeAdapter(result, mListener);
                listener.onOk("Data LoadFinished");
            }

            @Override
            public void onFail(Throwable throwable) {
                listener.onFail(throwable);
            }
        });
    }
}
