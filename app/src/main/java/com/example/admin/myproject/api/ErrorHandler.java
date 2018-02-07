package com.example.admin.myproject.api;

import android.app.Activity;
import android.widget.Toast;

import com.example.admin.myproject.R;
import com.example.admin.myproject.activities.WeatherInfoActivity;
import com.example.admin.myproject.utils.Utils;

/**
 * Created by admin on 2/7/2018.
 */

public class ErrorHandler {

    private static ErrorHandler errorHandler;

    private ErrorHandler() {
    }

    public static synchronized ErrorHandler getErrorHandler() {
        if (errorHandler == null) {
            errorHandler = new ErrorHandler();
        }
        return errorHandler;
    }

    public void handleError(Activity activity, Throwable e) {
        if (!Utils.isNetworkAvailable(activity)) {
            Toast.makeText(activity, activity.getString(R.string.no_connection), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
