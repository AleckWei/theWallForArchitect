package com.example.rxcodedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.rxcode.DayTwo.MapDemo.clearStudentList;
import static com.example.rxcode.DayTwo.MapDemo.testMapLikeDataFromNetwork;

public class DayTwoActivity extends AppCompatActivity {

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

        View btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStudentList();
            }
        });
    }
}