package com.mohamedabulgasem.mozulu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.api.DarkSkyApiClient;
import com.mohamedabulgasem.mozulu.data.api.DarkSkyApiService;
import com.mohamedabulgasem.mozulu.data.model.ForecastResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.DARK_SKY_API_SECRET_KEY;

/**
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public class DailyTempsActivity extends AppCompatActivity {

    @BindView(R.id.forecast_response_tv)
    TextView forecastResponseTv;

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_temps);
        ButterKnife.bind(this);
        setDailyTempsActivity();
    }

    /**
     *
     */
    private void setDailyTempsActivity() {

    }

    /**
     * @param menu Menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
