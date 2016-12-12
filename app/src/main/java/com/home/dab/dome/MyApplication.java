package com.home.dab.dome;

import android.app.Application;

/**
 * Created by DAB on 2016/12/12 15:14.
 */

public class MyApplication extends Application {
    private static MyApplication sMyApplication;

    public static MyApplication getMyApplication() {
        return sMyApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sMyApplication = this;
    }
}
