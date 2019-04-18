package com.mohamedabulgasem.mozulu.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public final class NetworkUtils {

    /**
     * This utility class is not instantiable
     */
    private NetworkUtils() {}

    /**
     * @param context Context
     * @return boolean
     */
    public static boolean networkIsConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnected();
        }
        return false;
    }

}
