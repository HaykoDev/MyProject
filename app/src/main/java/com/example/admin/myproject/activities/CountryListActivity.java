package com.example.admin.myproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.myproject.R;
import com.example.admin.myproject.adapter.CountryListAdapter;
import com.example.admin.myproject.api.ApiRequest;
import com.example.admin.myproject.api.models.Country;
import com.example.admin.myproject.utils.PrefConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2/6/2018.
 */

public class CountryListActivity extends AppCompatActivity {

    @BindView(R.id.rvCountries)
    RecyclerView rvCountries;

    private ArrayList<Country> countries;
    private CountryListAdapter countryListAdapter;
    private ApiRequest apiRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        ButterKnife.bind(this);
        apiRequest = ApiRequest.getInstance();
        initCountries();
        initList();
    }

    private void initCountries() {
        countries = new ArrayList<>();
        countries.add(new Country("Armenia",40.177200,44.503490));
        countries.add(new Country("Spain", 40.416775, -3.703790));
        countries.add(new Country("Italy", 43.769562, 11.255814));
        countries.add(new Country("Germany", 52.520008, 13.404954));
        countries.add(new Country("France", 48.864716, 2.349014));
        countries.add(new Country("England", 51.509865, -0.118092));
        countries.add(new Country("Brazil", -23.533773, -46.625290));
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCountries.setLayoutManager(linearLayoutManager);
        rvCountries.setHasFixedSize(true);
        countryListAdapter = new CountryListAdapter(this, countries);
        rvCountries.setAdapter(countryListAdapter);
    }


    @OnClick(R.id.btnShowWeather)
    public void showWeather() {
        Intent intent = new Intent(this, WeatherInfoActivity.class);
        intent.putExtra(PrefConstants.COUNTRIES, countryListAdapter.getCheckedCities());
        startActivity(intent);
    }
}
