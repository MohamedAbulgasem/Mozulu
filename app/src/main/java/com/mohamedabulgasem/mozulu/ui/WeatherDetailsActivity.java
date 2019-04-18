package com.mohamedabulgasem.mozulu.ui;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;

import com.mohamedabulgasem.mozulu.R;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public class WeatherDetailsActivity extends AppCompatActivity {

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

}
