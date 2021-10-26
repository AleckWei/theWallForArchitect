package com.example.news.base.imp;

import com.example.news.base.BaseCustomVM;

/**
 * 每个viewModel都要做一件事，就是绑定数据
 *
 * @param <T> 各种继承了BaseViewModel的vm
 */
public interface ICustomView<T extends BaseCustomVM> {

    void setData(T data);

}
