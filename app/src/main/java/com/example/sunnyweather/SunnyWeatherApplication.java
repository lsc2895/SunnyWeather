package com.example.sunnyweather;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class SunnyWeatherApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static final String TOKEN = "ojaGv5OvGMMwNqQT";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
