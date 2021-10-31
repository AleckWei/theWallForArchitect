package com.example.rxcodedemo;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxcode.CreateOperatorDemo;

import static com.example.rxcode.CreateOperatorDemo.test;
import static com.example.rxcode.CreateOperatorDemo.testFromArray;
import static com.example.rxcode.CreateOperatorDemo.testFromCallable;
import static com.example.rxcode.CreateOperatorDemo.testFromFuture;
import static com.example.rxcode.CreateOperatorDemo.testFromIterable;
import static com.example.rxcode.CreateOperatorDemo.testJust;
import static com.example.rxcode.FilterOperatorDemo.testFilter;
import static com.example.rxcode.MergeOperatorDemo.testConcat;
import static com.example.rxcode.MergeOperatorDemo.testConcatArray;
import static com.example.rxcode.ToolOperatorDemo.testSubscribeOn;
import static com.example.rxcode.TransOperatorDemo.testBuffer;
import static com.example.rxcode.TransOperatorDemo.testConcatMap;
import static com.example.rxcode.TransOperatorDemo.testFlatMap;
import static com.example.rxcode.TransOperatorDemo.testMap;

public class DNActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn);

        Button btnTest = findViewById(R.id.btn_create_operator_test);
        btnTest.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

        Button btnTest1 = findViewById(R.id.btn_create_operator_test1);
        btnTest1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOperatorDemo.test1();
            }
        });

        Button btnTestJust = findViewById(R.id.btn_test_just);
        btnTestJust.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testJust();
            }
        });

        Button btnTestFromArray = findViewById(R.id.btn_test_fromArray);
        btnTestFromArray.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromArray();
            }
        });

        Button btnTestFromIterable = findViewById(R.id.btn_test_fromIterable);
        btnTestFromIterable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromIterable();
            }
        });

        Button btnTestFromFuture = findViewById(R.id.btn_test_fromFuture);
        btnTestFromFuture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromFuture();
            }
        });

        Button btnTestFromCallable = findViewById(R.id.btn_test_fromCallable);
        btnTestFromCallable.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromCallable();
            }
        });

        Button btnTestMap = findViewById(R.id.btn_test_map);
        btnTestMap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testMap();
            }
        });

        Button btnTestFlatMap = findViewById(R.id.btn_test_flatMap);
        btnTestFlatMap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testFlatMap();
            }
        });

        Button btnConcatMap = findViewById(R.id.btn_test_concatMap);
        btnConcatMap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testConcatMap();
            }
        });

        Button btnBuffer = findViewById(R.id.btn_test_buffer);
        btnBuffer.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testBuffer();
            }
        });

        Button btnConcat = findViewById(R.id.btn_test_concat);
        btnConcat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testConcat();
            }
        });

        Button btnConcatArray = findViewById(R.id.btn_test_concat_array);
        btnConcatArray.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testConcatArray();
            }
        });

        Button btnSubscribeOn = findViewById(R.id.btn_test_subscribe_on);
        btnSubscribeOn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                testSubscribeOn();
            }
        });

        Button btnFilter = findViewById(R.id.btn_test_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testFilter();
            }
        });
    }
}