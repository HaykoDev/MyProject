package com.example.admin.myproject.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2/6/2018.
 */

public class ApiServiceFactory {

    public static String baseUrl = "https://www.metaweather.com/api/location/";

    public static ApiService getApiService() {
        return createRetrofit(baseUrl).create(ApiService.class);
    }

    private static Retrofit createRetrofit(String baseUrl) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return new Retrofit.Builder().baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
