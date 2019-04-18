package com.mohamedabulgasem.mozulu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.SHARED_PREF_NAME;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.SP_NIGHT_MODE;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
final class SharedPrefUtils {

    /**
     * This utility class is not instantiable
     */
    private SharedPrefUtils() {}

    /**
     * @param context Context
     * @param set boolean
     */
    static void setNightMode(Context context, boolean set) {
        getSharedPrefEditor(context).putBoolean(SP_NIGHT_MODE, set).apply();
    }

    /**
     * @param context Context
     * @return boolean
     */
    static boolean nightModeIsSet(Context context) {
        return getSharedPref(context).getBoolean(SP_NIGHT_MODE, false);
    }

            ///     SharedPrefUtils Handlers     ///
    /**
     * @param context Context
     * @return SharedPreferences
     */
    private static SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * @param context Context
     * @return SharedPreferences.Editor
     */
    private static SharedPreferences.Editor getSharedPrefEditor(Context context) {
        return getSharedPref(context).edit();
    }

}
