package com.example.zhujieapt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.annotation.BindView;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.tv_second_activity_content)
    TextView secondTextView;

    @BindView(R.id.btn_second)
    Button btnSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);
        secondTextView.setText("这是第二个activity");

        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondTextView.setText("点了之后");
            }
        });
    }
}