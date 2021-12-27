package com.example.acviewchange.acview;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.acviewchange.R;
import com.example.acviewchange.acview.databean.ItemBean;
import com.example.acviewchange.acview.viewimp.IPluginView;

public class AcView extends LinearLayout implements View.OnClickListener, IPluginView {

    private static final int HIDE_TIPS = 0;
    private final String mid;

    private TextView tvDeviceName, tvDeviceState, tvSendingCmd;

    private ImageView ivPower;

    AcViewBasePresenter mPresenter;

    private final Handler mHandler
            = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int what = msg.what;
            if (what == HIDE_TIPS) {
                tvSendingCmd.setVisibility(GONE);
                tvSendingCmd.setTextColor(getContext().getResources().getColor(R.color.black));
            }
            return false;
        }
    });

    public AcView(Context context) {
        super(context);
        initView(context);
        mid = "10001";
        initData();
    }

    public AcView(View rootView, ItemBean data) {
        super(rootView.getContext());
        initView(rootView.getContext(), rootView);
        mid = data.getMid();
        initData();
    }

    private void initView(Context context, View... parent) {
        View rootView = inflate(context, R.layout.ac_view, null);
        ConstraintLayout.LayoutParams lp =
                new ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        if (parent.length > 0) {
            ViewGroup.LayoutParams layoutParams = parent[0].getLayoutParams();
            lp.width = layoutParams.width;
            lp.height = layoutParams.height;
        } else {
            WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display defaultDisplay = systemService.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            int width = point.x;
            lp.width = (width - 10) / 2;
            lp.height = (int) ((width - 10) / 2 * 0.618);
        }

        rootView.setLayoutParams(lp);
        addView(rootView);

        tvDeviceName = rootView.findViewById(R.id.tv_device_name);
        tvDeviceState = rootView.findViewById(R.id.tv_device_state);
        tvSendingCmd = rootView.findViewById(R.id.tv_send_cmd);
        ivPower = rootView.findViewById(R.id.iv_power);
        ivPower.setOnClickListener(this);
    }

    private void initData() {
        AcViewBinder.bindPresenter(this, mid);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_power) {
            mPresenter.sendPowCmd();
        }
    }

    public void setState(ItemBean data) {
        tvDeviceName.setText(data.getDeviceName());
        tvDeviceState.setText(data.getDeviceState() == 1 ? "在线" : "离线");
        if (data.getPow() == 1) {
            showPowOnView();
        } else {
            showPowOffView();
        }
    }

    @Override
    public void showPowOnView() {
        Glide.with(this)
                .load(R.drawable.power_yellow)
                .into(ivPower);
    }

    @Override
    public void showPowOffView() {
        Glide.with(this)
                .load(R.drawable.power_black)
                .into(ivPower);
    }

    @Override
    public void setLoading(boolean loading) {
        if (tvSendingCmd != null) {
            tvSendingCmd.setTextColor(getContext().getResources().getColor(R.color.black));
            tvSendingCmd.setText(getContext().getResources().getString(R.string.ac_view_sending_cmd));
            if (loading) {
                tvSendingCmd.setVisibility(VISIBLE);
            } else {
                tvSendingCmd.setVisibility(GONE);
            }
        }
    }

    @Override
    public void showWrongTips(String tips) {
        tvSendingCmd.setVisibility(VISIBLE);
        tvSendingCmd.setText(tips);
        tvSendingCmd.setTextColor(getContext().getResources().getColor(R.color.red));
        mHandler.sendEmptyMessageDelayed(HIDE_TIPS, 2000);
    }


}
