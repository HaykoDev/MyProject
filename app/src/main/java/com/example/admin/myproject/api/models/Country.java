package com.example.admin.myproject.api.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2/6/2018.
 */

public class Country implements Parcelable {

    public static final Parcelable.Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
    private String name;
    private double latitude;
    private double longitude;

    public Country(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Country(Parcel in) {
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
