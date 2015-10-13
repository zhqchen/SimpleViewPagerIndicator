package com.zhqchen.viewpager.util;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * Created by zqchen
 */
public class DeviceUtil {

    public static int getDeviceScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getDeviceScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
