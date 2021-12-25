package com.example.acviewchange.dataimp;

public interface OnRequestListener<T> {

    void onOk(T result);

    void onFail(Throwable throwable);
}
