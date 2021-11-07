package com.example.rxCodeDemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rxCodeDemo.R;

import static com.example.rxtest.rxcode.day2.MapDemo.clearStudentList;
import static com.example.rxtest.rxcode.day2.MapDemo.testFlatMap;
import static com.example.rxtest.rxcode.day2.MapDemo.testMapInMap;
import static com.example.rxtest.rxcode.day2.MapDemo.testMapLikeDataFromNetwork;


public class Day2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_two);

        Button btnMap = findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testMapLikeDataFromNetwork();
            }
        });

        Button btnForListInMap = findViewById(R.id.btn_for_list_in_mapMethod);
        btnForListInMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testMapInMap();
            }
        });

        Button btnTestFlatMap = findViewById(R.id.btn_test_flatMap);
        btnTestFlatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testFlatMap();
            }
        });

        Button btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStudentList();
            }
        });
    }
}