package com.example.acviewchange.viewimp;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.acviewchange.acview.databean.ItemBean;

public interface UpdateViewListener {

    void convert(@NonNull View rootView, @NonNull ItemBean itemData, int position);

}
