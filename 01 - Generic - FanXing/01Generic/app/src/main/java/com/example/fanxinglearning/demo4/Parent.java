package com.example.fanxinglearning.demo4;

import android.util.Log;

public class Parent<E> {
    private E value;

    public E getValue() {
        Log.v("demo4", "Parent获取val ue");
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
