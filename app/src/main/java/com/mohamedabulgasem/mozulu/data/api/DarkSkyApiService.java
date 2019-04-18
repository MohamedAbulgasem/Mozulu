package com.mohamedabulgasem.mozulu.data.api;

import com.mohamedabulgasem.mozulu.data.model.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This Interface defines the contract for REST calls with the Dark Sky API.
 *
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public interface DarkSkyApiService {

    @GET("{key}/{latitude},{longitude}?exclude=minutely,alerts,flags&units=auto")
    Call<ForecastResponse> getForecast(@Path("key") String key, @Path("latitude") String latitude,
                                       @Path("longitude") String longitude);

}
