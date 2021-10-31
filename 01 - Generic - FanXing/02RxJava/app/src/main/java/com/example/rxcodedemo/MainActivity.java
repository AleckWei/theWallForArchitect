package com.example.rxcodedemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rxcodedemo.observer.TestActivity;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private final static String PATH = "http://pic1.win4000.com/wallpaper/c/53cdd1f7c1f21.jpg";

    private ProgressDialog mDialog;

    private ImageView ivTest;

    private final Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            Bitmap bitmap = (Bitmap) message.obj;
            ivTest.setImageBitmap(bitmap);
            if (mDialog != null) {
                mDialog.hide();
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialog = new ProgressDialog(MainActivity.this);

        ivTest = findViewById(R.id.iv_test);

        // 传统方法下载图片
        Button normalBtn = findViewById(R.id.btn_normal_download);
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.setTitle("正在普通下载。。。");
                mDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(PATH);
                            URLConnection urlConnection = url.openConnection();
                            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                            httpURLConnection.setConnectTimeout(5000);
                            int responseCode = httpURLConnection.getResponseCode();

                            if (HttpURLConnection.HTTP_OK == responseCode) {
                                Bitmap bitmap = BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                                Message msg = mHandler.obtainMessage();
                                msg.obj = bitmap;
                                mHandler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        // todo 下面使用rxJava思维高效写需求
        Button rxDownloadBtn = findViewById(R.id.btn_rx_download);
        rxDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rxJavaDownloadImageAction(view);
            }
        });

        Button rxOther = findViewById(R.id.btn_rx_others);
        rxOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

        Button btnActivityDN = findViewById(R.id.btn_dn_activity);
        btnActivityDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DNActivity.class);
                startActivity(intent);
            }
        });


    }

    private void rxJavaDownloadImageAction(View view) {
        // 2. 起点：PATH 事件源分发
        Observable.just(PATH)
                // 3. 事件流分发后，第一个拦截器对数据的形态做了转变
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String imagePath) throws Exception {
                        // 这里就可以进行耗时操作了
                        URL url = new URL(imagePath);
                        URLConnection urlConnection = url.openConnection();
                        HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                        httpURLConnection.setConnectTimeout(5000);
                        int responseCode = httpURLConnection.getResponseCode();

                        if (HttpURLConnection.HTTP_OK == responseCode) {
                            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
                        }
                        return null;
                    }
                })

                // 新增一个需求：加水印
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmapBefore) throws Exception {
                        // 中间加个水印
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setTextSize(100);
                        return drawWaterTextToBitmap(bitmapBefore,
                                "wwj", new Paint(), 100, 100);
                    }
                })

                // 为上游分出一个异步线程
                .subscribeOn(Schedulers.io())

                // 为下游分配Android主线程
                .observeOn(AndroidSchedulers.mainThread())

                // 订阅： 将起点和终点关联起来
                // 订阅的上面，全部是上游（放的是子线程，进行耗时操作）
                .subscribe(
                        // 订阅的下面，全部是下游（在主线程处理，进行ui操作）
                        new Observer<Bitmap>() {
                            // 这里就是终点，终点就是Observer观察者
                            @Override
                            public void onSubscribe(Disposable d) {
                                // 1. 一订阅就执行本函数
                                Log.d("wwj rxJava", "订阅 onSubscribe");
                                if (mDialog != null) {
                                    mDialog.setTitle("正在rxJava下载。。。");
                                    mDialog.show();
                                }
                            }

                            @Override
                            public void onNext(Bitmap bitmap) {
                                // 4. map中return后，就进入这里
                                // 在这里更新ui
                                if (bitmap != null) {
                                    ivTest.setImageBitmap(bitmap);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                // onError被调用了，就不会调用onComplete
                            }

                            @Override
                            public void onComplete() {
                                // 5. 这里就是最终点
                                // 代表rxJava的链条全部结束了
                                if (mDialog != null) {
                                    mDialog.hide();
                                }
                            }
                        }
                );
    }

    // 加水印的方法
    private Bitmap drawWaterTextToBitmap(Bitmap bitmap, String waterText,
                                         Paint paint, int paddingLeft, int paddingTop) {
        Bitmap.Config config = bitmap.getConfig();
        paint.setDither(true);
        paint.setFilterBitmap(true);

        if (config != null) {
            config = Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(config, true);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawText(waterText, paddingLeft, paddingTop, paint);
        return bitmap;
    }


}