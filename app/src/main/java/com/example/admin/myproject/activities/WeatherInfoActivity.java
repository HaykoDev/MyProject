package com.example.admin.myproject.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.admin.myproject.R;
import com.example.admin.myproject.api.ApiRequest;
import com.example.admin.myproject.api.ErrorHandler;
import com.example.admin.myproject.api.models.Country;
import com.example.admin.myproject.api.response.Weather;
import com.example.admin.myproject.utils.PrefConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by admin on 2/6/2018.
 */

public class WeatherInfoActivity extends AppCompatActivity {

    @BindView(R.id.tvWeather)
    TextView tvWeather;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ArrayList<Country> countries;
    private ApiRequest apiRequest;
    private Weather.ConsolidatedWeather consolidatedWeather;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        ButterKnife.bind(this);
        apiRequest = ApiRequest.getInstance();
        getExtras();
        getWeather();
    }

    private void getExtras() {
        countries = (ArrayList<Country>) getIntent().getSerializableExtra(PrefConstants.COUNTRIES);
    }

    private void getWeather() {
        if (countries != null && countries.size() > 0) {
            for (Country country : countries) {
                apiRequest.getWeather(country, new Subscriber<Weather>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        ErrorHandler.getErrorHandler().handleError(WeatherInfoActivity.this, e);
                    }

                    @Override
                    public void onNext(Weather weather) {
                        progressBar.setVisibility(View.GONE);
                        setWeatherInfo(weather, country);
                    }
                });
            }
        }
    }

    private void setWeatherInfo(Weather weather, Country country) {
        consolidatedWeather = weather.getConsolidatedWeathers().get(0);
        tvWeather.append("Country name: " + country.getName() + "\n" + "Air Pressure: " + consolidatedWeather.getAirPressure() + "\n"
                + "humidity: " + consolidatedWeather.getHumidity() + "\n" + "temp: " +
                consolidatedWeather.getTemp() + "\n" + "wind speed: " + consolidatedWeather.getWindSpeed());
        tvWeather.append("\n");
        tvWeather.append("\n");
    }
}
