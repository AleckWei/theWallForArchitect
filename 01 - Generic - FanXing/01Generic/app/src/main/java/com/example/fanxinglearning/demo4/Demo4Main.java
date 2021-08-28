package com.example.fanxinglearning.demo4;

import android.util.Log;

public class Demo4Main {
    private static final String TAG = "泛型";
    private static final String TAG_DEMO4 = TAG + "demo4";

    public static void main() {

        ChildFirst<String> childFirst = new ChildFirst<>();
        childFirst.setValue("childFirst");
        String childFirstValue = childFirst.getValue();
        Log.v(TAG_DEMO4, "拿到的child First value：" + childFirstValue);

        ChildSecond childSecond = new ChildSecond();
        childSecond.setValue(123);
        Integer childSecondValue = childSecond.getValue();
        Log.v(TAG_DEMO4, "拿到的child Second value：" + childSecondValue);

    }
}
