package com.mohamedabulgasem.mozulu.data;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;

import com.mohamedabulgasem.mozulu.data.api.DarkSkyApiClient;
import com.mohamedabulgasem.mozulu.data.model.Forecast;
import com.mohamedabulgasem.mozulu.data.model.datapoints.DailyDataPoint;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.DARK_SKY_API_SECRET_KEY;

/**
 * Created by Mohamed Abulgasem on 2019/04/17.
 */
public final class ForecastRepository {

    private static final String TAG = ForecastRepository.class.getSimpleName();
    private static ForecastRepository instance = null;

    private Forecast forecast;
    private Location location;

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
     * @return Forecast
     */
    public Forecast getForecast() {
        return forecast;
    }

    /**
     * @return Location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param newLocation current user location to query the dark sky API.
     */
    public void loadForecast(Location newLocation) {
        location = newLocation;
        String key = new String(Base64.decode(DARK_SKY_API_SECRET_KEY, Base64.DEFAULT));
        String latitude = String.valueOf(location.getLatitude());
        String longitude = String.valueOf(location.getLongitude());
        DarkSkyApiClient.getService()
                .getForecast(key, latitude, longitude)
                .retry(3)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .doOnError(throwable -> {
                    Log.e(TAG, "Forecast request failed because: \n" + throwable.getMessage());
                    EventBus.getDefault().post(false);
                })
                .doOnSuccess(aForecast -> {
                    forecast = aForecast;
                    EventBus.getDefault().post(true);
                })
                .subscribe();
    }

    /**
     * Get DailyDataPoint object by their natural position in the list.
     *
     * @param position int
     * @return DailyDataPoint
     */
    public DailyDataPoint getDayByPosition(int position) {
        return forecast.getDaily().getData().get(position);
    }

    /**
     * reset ForecastRepository so that a new call to the dark sky api can be established
     */
    public void reset() {
        forecast = null;
        location = null;
    }

}
