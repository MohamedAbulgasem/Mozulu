package com.mohamedabulgasem.mozulu.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mohamedabulgasem.mozulu.data.api.DarkSkyApiClient;
import com.mohamedabulgasem.mozulu.data.api.DarkSkyApiService;
import com.mohamedabulgasem.mozulu.data.model.ForecastResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.DARK_SKY_API_SECRET_KEY;

/**
 * Created by Mohamed Abulgasem on 2019/04/17.
 */
public final class ForecastRepository {

    private static ForecastRepository instance = null;
    private ForecastResponse forecast;

    /**
     * Private default constructor to ensure that this singleton
     * gets instantiated using the getInstance() method.
     */
    private ForecastRepository() {}

    /**
     * @return ForecastRepository
     */
    public static ForecastRepository getInstance() {
        if (instance == null) {
            instance = new ForecastRepository();
        }
        return instance;
    }

    /**
     * @return ForecastResponse
     */
    public ForecastResponse getForecast() {
        return forecast;
    }

    /**
     *
     */
    public void loadForecast() {
        DarkSkyApiService apiService = DarkSkyApiClient.getRetrofit().create(DarkSkyApiService.class);
        apiService.getForecast(DARK_SKY_API_SECRET_KEY, "-33.9249", "18.4241")
                .enqueue(new Callback<ForecastResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<ForecastResponse> call,
                                           @NonNull Response<ForecastResponse> response) {
                        if (response.isSuccessful() && null != response.body()) {
                            forecast = response.body();
                        } else {
                            Log.e("ForecastResponse", "ForecastResponse is NULL!");
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<ForecastResponse> call, @NonNull Throwable t) {
                        Log.e("onFailure", "onFailure because: \n" + t.getMessage());
                    }
                });
    }

}
