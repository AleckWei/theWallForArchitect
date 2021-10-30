package com.example.rxcodedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxcode.CreateOperatorDemo;

import static com.example.rxcode.CreateOperatorDemo.test;
import static com.example.rxcode.CreateOperatorDemo.testFromArray;
import static com.example.rxcode.CreateOperatorDemo.testFromCallable;
import static com.example.rxcode.CreateOperatorDemo.testFromFuture;
import static com.example.rxcode.CreateOperatorDemo.testFromIterable;
import static com.example.rxcode.CreateOperatorDemo.testJust;

public class DNActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dn);

        Button btnTest = findViewById(R.id.btn_create_operator_test);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

        Button btnTest1 = findViewById(R.id.btn_create_operator_test1);
        btnTest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOperatorDemo.test1();
            }
        });

        Button btnTestJust = findViewById(R.id.btn_test_just);
        btnTestJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testJust();
            }
        });

        Button btnTestFromArray = findViewById(R.id.btn_test_fromArray);
        btnTestFromArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromArray();
            }
        });

        Button btnTestFromIterable = findViewById(R.id.btn_test_fromIterable);
        btnTestFromIterable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromIterable();
            }
        });

        Button btnTestFromFuture = findViewById(R.id.btn_test_fromFuture);
        btnTestFromFuture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromFuture();
            }
        });

        Button btnTestFromCallable = findViewById(R.id.btn_test_fromCallable);
        btnTestFromCallable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testFromCallable();
            }
        });

    }
}