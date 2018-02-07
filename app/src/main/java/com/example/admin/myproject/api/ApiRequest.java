package com.example.admin.myproject.api;

import com.example.admin.myproject.MyApplication;
import com.example.admin.myproject.api.models.Country;
import com.example.admin.myproject.api.response.Weather;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2/6/2018.
 */

public class ApiRequest {

    private static ApiRequest instance;

    private ApiRequest() {
    }

    public static synchronized ApiRequest getInstance() {
        if (instance == null) {
            instance = new ApiRequest();
        }
        return instance;
    }

    public void getWeather(Country country, Subscriber<Weather> subscriber) {
        String query = country.getLatitude() + "," + country.getLongitude();
        Observable<Weather> observable = MyApplication.getApiService().getCountryId(query).
                flatMap(countryInfo -> getWhether(countryInfo.get(0).getWoeid()))
                .subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
        if (subscriber != null) {
            observable.subscribe(subscriber);
        }
    }

    private Observable<Weather> getWhether(String id) {
        return MyApplication.getApiService().getWheather(id);
    }
}
