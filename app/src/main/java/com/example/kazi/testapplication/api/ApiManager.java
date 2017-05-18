package com.example.kazi.testapplication.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kazi on 5/May/17.
 */

public class ApiManager {

    private Retrofit mRetrofit;
    private ApiService mApiService;

    public void initialize(String baseURL) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        if (mApiService == null) {
            mApiService = mRetrofit.create(ApiService.class);
        }
        return mApiService;
    }
}
