package com.hebaelsaid.android.newsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.util.Log;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.HttpException;

public abstract class CommonFunction {

    public static Boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR");
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI");
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET");
                    return true;
                }
            }
        }
        return false;
    }

    public static String apisHandleError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            int statusCode = httpException.code();
            // handle different HTTP error codes here (4xx)
            return "api error " + statusCode;
        } else if (throwable instanceof SocketTimeoutException) {
            // handle timeout from Retrofit
            return "api error " + throwable.getMessage();
        } else if (throwable instanceof IOException) {
            // file was not found, do something
            return "api error " + throwable.getMessage();
        } else {
            // generic error handling
            return "api internet error ";
        }
    }
}
