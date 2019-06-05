package com.mohamedabulgasem.mozulu.utils;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public final class AppConstants {

    public static final String DARK_SKY_API_BASE_URL = "https://api.darksky.net/forecast/";
    public static final String DARK_SKY_API_SECRET_KEY = "MDY1ZmMyMzhmYWUwMTMwY2RmN2I2MGUyZmVhMTY5NmE=";
    public static final int LOCATION_REQUEST_CODE = 1212;
    // Intent related constants
    public static final String DAILY_POSITION = "DAILY_POSITION";
    public static final String INTENT_ACTION_VIEW = "android.intent.action.VIEW";
    // Forecast possible icon constants
    static final String CLEAR_DAY = "clear-day";
    static final String CLEAR_NIGHT = "clear-night";
    static final String RAIN = "rain";
    static final String SNOW = "snow";
    static final String SLEET = "sleet";
    static final String WIND = "wind";
    static final String FOG = "fog";
    static final String CLOUDY = "cloudy";
    static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    // SharedPrefUtils constants
    static final String SHARED_PREF_NAME = "userInfo";
    static final String SP_NIGHT_MODE = "nightMode";

    /**
     * This utility class is not instantiable
     */
    private AppConstants() {}

}
