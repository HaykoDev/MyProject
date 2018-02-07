package com.example.admin.myproject.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2/6/2018.
 */

public class CountryInfo {

    @SerializedName("distance")
    private String distance;

    @SerializedName("title")
    private String title;

    @SerializedName("location_type")
    private String locationType;

    @SerializedName("woeid")
    private String woeid;

    @SerializedName("latt_long")
    private String lattLong;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getWoeid() {
        return woeid;
    }

    public void setWoeid(String woeid) {
        this.woeid = woeid;
    }

    public String getLattLong() {
        return lattLong;
    }

    public void setLattLong(String lattLong) {
        this.lattLong = lattLong;
    }
}
