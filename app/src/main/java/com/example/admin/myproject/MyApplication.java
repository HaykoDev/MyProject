package com.example.admin.myproject;

import android.app.Application;

import com.example.admin.myproject.api.ApiService;
import com.example.admin.myproject.api.ApiServiceFactory;

/**
 * Created by admin on 2/6/2018.
 */

public class MyApplication extends Application {

    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = ApiServiceFactory.getApiService();
        }
        return apiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
