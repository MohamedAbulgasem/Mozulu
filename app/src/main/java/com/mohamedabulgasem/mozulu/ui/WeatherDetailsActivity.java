package com.mohamedabulgasem.mozulu.ui;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.ForecastRepository;
import com.mohamedabulgasem.mozulu.data.model.WeatherDetail;
import com.mohamedabulgasem.mozulu.data.model.datapoints.DailyDataPoint;
import com.mohamedabulgasem.mozulu.ui.adapters.WeatherDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.DAILY_POSITION;
import static com.mohamedabulgasem.mozulu.utils.DateUtils.getTime;
import static com.mohamedabulgasem.mozulu.utils.DateUtils.getWeatherDetailsDate;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.getDisplayTemperature;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.setWeatherIcon;

/**
 * Created by Mohamed Abulgasem on 2019/04/22.
 */
public class WeatherDetailsActivity extends AppCompatActivity {

    @BindView(R.id.weather_details_day_tv)
    TextView dateTv;

    @BindView(R.id.weather_details_icon_iv)
    ImageView iconIv;

    @BindView(R.id.weather_details_max_temperature_tv)
    TextView maxTempTv;

    @BindView(R.id.weather_details_min_temperature_tv)
    TextView minTempTv;

    @BindView(R.id.weather_details_summary_tv)
    TextView summaryTv;

    @BindView(R.id.weather_details_rv)
    RecyclerView weatherDetailsRv;

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
        ButterKnife.bind(this);
        setWeatherDetailsActivity();
    }

    /**
     *
     */
    private void setWeatherDetailsActivity() {
        int dayPosition = getIntent().getIntExtra(DAILY_POSITION, 0);
        DailyDataPoint day = ForecastRepository.getInstance().getDayByPosition(dayPosition);
        setWeatherDetailsTopSection(day);
        setWeatherDetailsRecyclerView(day);
    }

    /**
     * @param day DailyDataPoint
     */
    private void setWeatherDetailsTopSection(DailyDataPoint day) {
        String date = getWeatherDetailsDate(day.getTime());
        String icon = day.getIcon().trim();
        String maxTemp = getDisplayTemperature(day.getTemperatureMax());
        String minTemp = getDisplayTemperature(day.getTemperatureMin());
        String summary = day.getSummary();
        dateTv.setText(date);
        setWeatherIcon(iconIv, icon);
        maxTempTv.setText(maxTemp);
        minTempTv.setText(minTemp);
        summaryTv.setText(summary);
    }

    /**
     * @param day DailyDataPoint
     */
    private void setWeatherDetailsRecyclerView(DailyDataPoint day) {
        WeatherDetailsAdapter adapter = new WeatherDetailsAdapter(getWeatherDetailsList(day));
        weatherDetailsRv.setLayoutManager(new LinearLayoutManager(this));
        weatherDetailsRv.setAdapter(adapter);
        weatherDetailsRv.setFocusable(false);
    }

    /**
     * @param day DailyDataPoint
     * @return List<WeatherDetail>
     */
    private List<WeatherDetail> getWeatherDetailsList(DailyDataPoint day) {
        String humidity = Math.round(day.getHumidity() * 100) + "%";
        String pressure = Math.round(day.getPressure()) + " hPa";
        String windSpeed = Math.round(day.getWindSpeed()) + " km/h";
        String cloudCover = Math.round(day.getCloudCover() * 100) + "%";
        String visibility = Math.round(day.getVisibility()) + " km";
        String sunrise = getTime(day.getSunriseTime());
        String sunset = getTime(day.getSunsetTime());
        List<WeatherDetail> weatherDetails = new ArrayList<>();
        weatherDetails.add(new WeatherDetail(R.drawable.ic_humidity, "Humidity", humidity));
        weatherDetails.add(new WeatherDetail(R.drawable.ic_pressure, "Pressure", pressure));
        weatherDetails.add(new WeatherDetail(R.drawable.ic_wind_speed, "Wind Speed", windSpeed));
        weatherDetails.add(new WeatherDetail(R.drawable.ic_cloud_cover, "Cloud Cover", cloudCover));
        weatherDetails.add(new WeatherDetail(R.drawable.ic_visibility, "Visibility", visibility));
        weatherDetails.add(new WeatherDetail(R.drawable.ic_sunrise, "Sunrise", sunrise));
        weatherDetails.add(new WeatherDetail(R.drawable.ic_sunset, "Sunset", sunset));
        return weatherDetails;
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

}
