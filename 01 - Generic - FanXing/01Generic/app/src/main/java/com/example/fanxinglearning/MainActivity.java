package com.example.fanxinglearning;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fanxinglearning.demo2.Demo2Main;
import com.example.fanxinglearning.demo3.Demo3Main;
import com.example.fanxinglearning.demo4.Demo4Main;
import com.example.fanxinglearning.demo5.Demo5Main;
import com.example.fanxinglearning.demo6.Demo6Main;
import com.example.fanxinglearning.demo7.Demo7Main;
import com.example.fanxinglearning.demo8.Demo8Main;
import com.example.fanxinglearning.demo8_1.Demo8_1Main;
import com.example.fanxinglearning.demo9.Demo9Main;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "泛型";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // demo2
        Demo2Main.main();
        // demo3
        Demo3Main.main();
        // demo4
        Demo4Main.main();
        // demo5
        Demo5Main.main();
        // demo6
        Demo6Main.main();
        // demo7
        Demo7Main.main();
        // demo8
        Demo8Main.main();
        // demo8_1
        Demo8_1Main.main();
        // demo9
        Demo9Main.main();
    }
}