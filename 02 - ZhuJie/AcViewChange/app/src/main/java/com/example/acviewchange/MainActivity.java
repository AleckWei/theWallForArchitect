package com.example.acviewchange;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acviewchange.dataimp.OnRequestListener;
import com.example.acviewchange.presenter.MainPresenter;
import com.example.acviewchange.recyclerview.viewholder.ItemDecoration;
import com.example.acviewchange.viewimp.IMainView;
import com.example.annotation.ButterKnife.ButterKnife;
import com.example.annotation.ButterKnife.annotation.BindView;

public class MainActivity extends AppCompatActivity implements IMainView {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @BindView(R.id.rl_load_fail)
    LinearLayout rlLoadFailedLayout;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定控件
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);

        loadData();
        rvMain.addItemDecoration(new ItemDecoration(2, 10, true));
        rvMain.setLayoutManager(new GridLayoutManager(this, 2));

        setListener();
    }

    private void setListener() {
        rlLoadFailedLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        mPresenter.initAdapter(new OnRequestListener<String>() {
            @Override
            public void onOk(String result) {
                rvMain.setAdapter(mPresenter.getAdapter());
                rvMain.setVisibility(View.VISIBLE);
                rlLoadFailedLayout.setVisibility(View.GONE);
            }

            @Override
            public void onFail(Throwable throwable) {
                rvMain.setVisibility(View.GONE);
                rlLoadFailedLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}