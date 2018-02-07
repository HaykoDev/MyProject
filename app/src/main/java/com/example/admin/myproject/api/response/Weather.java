package com.example.admin.myproject.api.response;

/**
 * Created by admin on 2/6/2018.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Weather {

    @SerializedName("consolidated_weather")
    private ArrayList<ConsolidatedWeather> consolidatedWeathers;

    public ArrayList<ConsolidatedWeather> getConsolidatedWeathers() {
        return consolidatedWeathers;
    }

    public void setConsolidatedWeathers(ArrayList<ConsolidatedWeather> consolidatedWeathers) {
        this.consolidatedWeathers = consolidatedWeathers;
    }

    public class ConsolidatedWeather {

        @SerializedName("the_temp")
        private String temp;

        @SerializedName("wind_speed")
        private String windSpeed;

        @SerializedName("air_pressure")
        private String airPressure;

        @SerializedName("humidity")
        private String humidity;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getAirPressure() {
            return airPressure;
        }

        public void setAirPressure(String airPressure) {
            this.airPressure = airPressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }

}



