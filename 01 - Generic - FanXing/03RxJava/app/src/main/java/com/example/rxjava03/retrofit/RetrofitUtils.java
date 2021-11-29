package com.example.rxjava03.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static ApiService apiService;
    private static Retrofit retrofit;

    private static void init() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        RequestBody body = chain.call().request().body();
//                        long l = body.contentLength();
//                        Log.d("wwj", "请求长度： " + l);
//                        return null;
//                    }
//                })
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    public static ApiService getService() {
        if (apiService == null) {
            init();
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }

}
