package com.example.rxjava03;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxjava03.leak.LeakActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * rxJava 的 三个事件流概念
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doInThread();
            }
        });

        Button btnIntentActivity2 = findViewById(R.id.btn_intent_activity2);
        btnIntentActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LeakActivity.class);
                startActivity(intent);
            }
        });

        String testA = "[\"1\",null, \"ABCD\"]";
        String testB = "[1,null,ABCD]";

        Button btnTestMyMethod = findViewById(R.id.btn_test_my_method);
        btnTestMyMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b = myMethod(testA, testB);
                Log.d("wwj", "是否相等？ " + b);
            }
        });

    }

    public static boolean myMethod(String strA, String strB) {
        if (strA == null && strB == null) {
            return true;
        }
        if (strA == null) {
            return false;
        }
        if (strB == null) {
            return false;
        }
        strA = strA.replace("\"", "")
                .replace("'", "")
                .replace("[", "")
                .replace("]", "");
        Log.d("wwj", "拆解后的strA： " + strA);

        String[] strAArrays = strA.split(",");
        List<Double> strAToDoubleList = new ArrayList<>();

        strB = strB.replace("\"", "")
                .replace("'", "")
                .replace("[", "")
                .replace("]", "");

        Log.d("wwj", "拆解后的strB： " + strB);

        String[] strBArrays = strB.split(",");
        List<Double> strBToDoubleList = new ArrayList<>();

        for (int index = 0; index < strAArrays.length; index++) {
            double strToDouble = 0.0;
            try {
                strToDouble = Double.parseDouble(strAArrays[index]);
                strAToDoubleList.add(strToDouble);
            } catch (Exception e) {
                if (index >= strBArrays.length) {
                    return false;
                }
                if (TextUtils.equals(strAArrays[index].trim(), strBArrays[index].trim())) {
                    strAToDoubleList.add(0.0);
                } else {
                    return false;
                }
            }
        }

        for (int index = 0; index < strBArrays.length; index++) {
            double strToDouble = 0.0;
            try {
                strToDouble = Double.parseDouble(strBArrays[index]);
                strBToDoubleList.add(strToDouble);
            } catch (Exception e) {
                strBToDoubleList.add(0.0);
            }
        }

        if (strAToDoubleList.size() != strBToDoubleList.size()) {
            return false;
        } else {
            for (int index = 0; index < strAToDoubleList.size(); index++) {
                if (!strAToDoubleList.get(index).equals(strBToDoubleList.get(index))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void doInThread() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                // 事件发射源在这
                Log.d("wwj", "subscribe thread" + Thread.currentThread());
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return o;
            }
        }).subscribeOn(Schedulers.io())
                .flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Object o) throws Exception {
                        return Observable.just(o);
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}