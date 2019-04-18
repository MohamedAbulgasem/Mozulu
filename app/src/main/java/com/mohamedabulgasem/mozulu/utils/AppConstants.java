package com.mohamedabulgasem.mozulu.utils;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public final class AppConstants {

    public static final String DARK_SKY_API_BASE_URL = "https://api.darksky.net/forecast/";
    public static final String DARK_SKY_API_SECRET_KEY = "065fc238fae0130cdf7b60e2fea1696a";
    // ForecastResponse icon constants
    public static final String CLEAR_DAY = "clear-day";
    public static final String CLEAR_NIGHT = "clear-night";
    public static final String RAIN = "rain";
    public static final String SNOW = "snow";
    public static final String SLEET = "sleet";
    public static final String WIND = "wind";
    public static final String FOG = "fog";
    public static final String CLOUDY = "cloudy";
    public static final String PARTLY_CLOUDY_DAY = "partly-cloudy-day";
    public static final String PARTLY_CLOUDY_NIGHT = "partly-cloudy-night";
    // Intent related constants
    public static final String INTENT_ACTION_VIEW = "android.intent.action.VIEW";
    // SharedPrefUtils constants
    static final String SHARED_PREF_NAME = "userInfo";
    static final String SP_NIGHT_MODE = "nightMode";

    /**
     * This utility class is not instantiable
     */
    private AppConstants() {}

}
