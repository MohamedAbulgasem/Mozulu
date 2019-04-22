package com.mohamedabulgasem.mozulu.data.api;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.DARK_SKY_API_BASE_URL;

/**
 * This is boundary class for interacting with the Dark Sky REST API.
 *
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public final class DarkSkyApiClient {

    private static final String TAG = DarkSkyApiClient.class.getSimpleName();
    private static DarkSkyApiService service = null;

    /**
     * Private default constructor to ensure appropriate usage of this class
     */
    private DarkSkyApiClient() {}

    /**
     * @return DarkSkyApiService
     */
    public static DarkSkyApiService getService() {
        if (service == null) {
            service = new Retrofit
                    .Builder()
                    .baseUrl(DARK_SKY_API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DarkSkyApiService.class);
            Log.d(TAG, "Dark Sky API Service Initialised");
        }
        return service;
    }

}