package com.mohamedabulgasem.mozulu.data.api;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.DARK_SKY_API_BASE_URL;

/**
 * This is boundary class for interacting with the Events REST API.
 *
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public final class DarkSkyApiClient {

    private static final String TAG = DarkSkyApiClient.class.getSimpleName();
    private static Retrofit retrofit = null;

    /**
     * @return Retrofit
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(DARK_SKY_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d(TAG, "Dark Sky API Client Initialised");
        }
        return retrofit;
    }

}