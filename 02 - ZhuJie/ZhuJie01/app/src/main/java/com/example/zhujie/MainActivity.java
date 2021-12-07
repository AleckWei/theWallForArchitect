package com.example.zhujie;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zhujie.reflection.Test06;

/**
 * 注解
 * 1) 为程序提供一些解释，标识，标志，检查
 * 2) 提供参数的配置，供程序使用
 * 3) 可以一定程度上地代替配置文件的使用
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        TestReflection.main(new String[]{"wwj"});

//        try {
//            Test03.main();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        Test04.main();
//        Test05.main();
        Test06.main();
    }
}