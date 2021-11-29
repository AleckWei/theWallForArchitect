package com.example.rxjava03.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    // 这里只是举个例子
    @GET("user/info")
    Observable<String> getUserInfo();

}
