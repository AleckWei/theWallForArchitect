package com.example.acviewchange.acview.viewimp;

import com.example.acviewchange.acview.databean.ItemBean;

public interface IPluginView {
    void setState(ItemBean data);

    void showPowOnView();

    void showPowOffView();

    void setLoading(boolean loading);

    /**
     * 右下角展示错误提示
     *
     * @param tips 提示内容
     */
    void showWrongTips(String tips);

}
