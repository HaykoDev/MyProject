package com.example.admin.myproject.api;

import com.example.admin.myproject.api.response.CountryInfo;
import com.example.admin.myproject.api.response.Weather;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2/6/2018.
 */

public interface ApiService {

    @GET("search")
    Observable<ArrayList<CountryInfo>> getCountryId(@Query("lattlong") String latLng);

    @GET("{id}")
    Observable<Weather> getWheather(@Path("id") String id);

}
