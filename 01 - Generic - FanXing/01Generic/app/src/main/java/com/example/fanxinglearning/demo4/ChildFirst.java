package com.example.fanxinglearning.demo4;

import android.util.Log;

/**
 * 泛型类派生子类，当子类也是泛型类时，那么子类的泛型标识要和父类的一致
 *
 * @param <T>
 */
public class ChildFirst<T> extends Parent<T> {
    @Override
    public T getValue() {
        Log.v("demo4", "childFirst获取value");
        return super.getValue();
    }
}
