package com.zhqchen.viewpager;

import android.app.Application;
import android.content.Context;

/**
 * Created by chen on 15/10/13.
 */
public class SimpleIndicatorApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
