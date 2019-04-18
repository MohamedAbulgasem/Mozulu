package com.mohamedabulgasem.mozulu.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.BuildConfig;
import com.mohamedabulgasem.mozulu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.INTENT_ACTION_VIEW;
import static com.mohamedabulgasem.mozulu.utils.NightModeUtils.nightModeIsSet;
import static com.mohamedabulgasem.mozulu.utils.NightModeUtils.setNightMode;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.night_mode_switch)
    Switch nightModeSwitch;

    @BindView(R.id.version_number_tv)
    TextView versionNoTextView;

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        setSettingsActivity();
    }

    /**
     * Handle interactions with the night mode switch and set up
     * initial state of the switch when settings activity is displayed
     */
    private void setSettingsActivity() {
        setNightModeSwitchInitialState();
        setNightModeSwitchListener();
        setVersionNoTextView();
    }

    /**
     *
     */
    private void setNightModeSwitchInitialState() {
        if (nightModeIsSet(getApplicationContext())) {
            nightModeSwitch.setChecked(true);
            nightModeSwitch.setText(R.string.settings_on);
        } else {
            nightModeSwitch.setChecked(false);
            nightModeSwitch.setText(R.string.settings_off);
        }
    }

    /**
     *
     */
    private void setNightModeSwitchListener() {
        nightModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                nightModeSwitch.setText(R.string.settings_on);
                setNightMode(getApplicationContext(), true);
            } else {
                nightModeSwitch.setText(R.string.settings_off);
                setNightMode(getApplicationContext(), false);
            }
            recreate();
        });
    }

    /**
     *
     */
    private void setVersionNoTextView() {
        String version = BuildConfig.VERSION_NAME + "." + BuildConfig.VERSION_CODE;
        versionNoTextView.setText(version);
    }

    /**
     * @param view View
     */
    public void onDarkSkyReferenceLinkClicked(View view) {
        Intent intent = new Intent(INTENT_ACTION_VIEW);
        // TODO: Add appropriate user lat & log values
        Uri uri = Uri.parse("https://darksky.net/forecast/-33.9249,18.4241/ca12/en");
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        NavUtils.navigateUpFromSameTask(this);
    }

}
