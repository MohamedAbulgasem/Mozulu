package com.mohamedabulgasem.mozulu.utils;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public final class NightModeUtils {

    /**
     * This utility class is not instantiable
     */
    private NightModeUtils() {}

    /**
     * @param context Context
     * @param set boolean
     */
    public static void setNightMode(Context context, boolean set) {
        SharedPrefUtils.setNightMode(context, set);
        if (set) {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * @param context Context
     * @return boolean
     */
    public static boolean nightModeIsSet(Context context) {
        return SharedPrefUtils.nightModeIsSet(context);
    }

}