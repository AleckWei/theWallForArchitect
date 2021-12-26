package com.example.acviewchange.acview.viewimp;

import com.example.acviewchange.acview.databean.ItemBean;

public interface IPluginView {
    void setState(ItemBean data);

    void showPowOnView();

    void showPowOffView();

    void setLoading(boolean loading);

}
