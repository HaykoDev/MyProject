package com.example.admin.myproject.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import com.example.admin.myproject.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by admin on 2/6/2018.
 */

public class Utils {

    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static void showGPSDialog(final AppCompatActivity appCompatActivity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(appCompatActivity);
        builder.setMessage(appCompatActivity.getString(R.string.gps_alert_dialog))
                .setCancelable(false)
                .setPositiveButton(appCompatActivity.getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.dismiss();
                        appCompatActivity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(appCompatActivity.getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        appCompatActivity.finish();
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static LatLng getMiddle(Marker origin, Marker dest) {
        double dLon = Math.toRadians(origin.getPosition().longitude - dest.getPosition().longitude);
        double destLatitude = Math.toRadians(dest.getPosition().latitude);
        double originLatitude = Math.toRadians(origin.getPosition().latitude);
        double destLongitude = Math.toRadians(dest.getPosition().longitude);

        double Bx = Math.cos(originLatitude) * Math.cos(dLon);
        double By = Math.cos(originLatitude) * Math.sin(dLon);
        double middleLatitude = Math.atan2(Math.sin(destLatitude) + Math.sin(originLatitude), Math.sqrt((Math.cos(destLatitude) + Bx) * (Math.cos(destLatitude) + Bx) + By * By));
        double middleLongitude = destLongitude + Math.atan2(By, Math.cos(destLatitude) + Bx);
        middleLatitude = Math.toDegrees(middleLatitude);
        middleLongitude = Math.toDegrees(middleLongitude);
        return new LatLng(middleLatitude, middleLongitude);
    }

    public static Bitmap createBitmapFromView(View v) {
        v.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(v.getMeasuredWidth(),
                v.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(bitmap);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return bitmap;
    }

    public static double getDisplayWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
