package com.mohamedabulgasem.mozulu.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import static android.support.v7.app.AppCompatDelegate.setDefaultNightMode;
import static com.mohamedabulgasem.mozulu.utils.NightModeUtils.nightModeIsSet;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public class LauncherActivity extends AppCompatActivity {

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (nightModeIsSet(getApplicationContext())) {
            setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, DailyTempsActivity.class));
        finish();
    }

}
