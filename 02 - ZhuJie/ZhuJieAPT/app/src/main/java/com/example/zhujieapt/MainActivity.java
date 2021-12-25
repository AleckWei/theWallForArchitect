package com.example.zhujieapt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.BindString;
import com.example.annotation.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_main_content)
    TextView tvMainContent;

    @BindView(R.id.btn_first)
    Button btnFirst;

    @BindString(R.string.app_name)
    String appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        tvMainContent.setText("我是韦武浚");

        btnFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}