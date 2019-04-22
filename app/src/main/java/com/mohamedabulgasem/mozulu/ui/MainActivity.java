package com.mohamedabulgasem.mozulu.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.ForecastRepository;
import com.mohamedabulgasem.mozulu.data.model.Forecast;
import com.mohamedabulgasem.mozulu.utils.ErrorCode;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.mohamedabulgasem.mozulu.utils.ErrorCode.*;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.*;
import static com.mohamedabulgasem.mozulu.utils.NetworkUtils.noNetworkConnectivity;

/**
 * This activity is the main entry point to the application.
 * It handles the logic behind retrieving user location, firing off calls to the API
 * and handling and displaying errors. Once forecast data is available, it shows ForecastFragment
 * to display Forecast details.
 *
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    @BindView(R.id.loading_indicator)
    ProgressBar loadingIndicator;

    @BindView(R.id.error_layout)
    CardView errorLayout;

    @BindView(R.id.error_icon_iv)
    ImageView errorIconIv;

    @BindView(R.id.error_message_tv)
    TextView errorMessageTv;

    @BindView(R.id.error_btn)
    Button errorBtn;

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setDailyTempsActivity();
    }

    /**
     * Check if Forecast is cached in memory, display Forecast fragment else
     * check if there is internet connectivity, get user location and query for weather info.
     */
    private void setDailyTempsActivity() {
        if (getForecast() != null) {
            displayForecastFragment();
        } else if (noNetworkConnectivity(this)){
            displayErrorScreen(NO_CONNECTIVITY);
        } else {
            getLocationAndLoadForecast();
        }
    }

    /**
     *
     */
    private void displayForecastFragment() {
        showFragmentContainer();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ForecastFragment(), ForecastFragment.TAG)
                .commit();
    }

    /**
     * Check if permission is not granted, ask the user else fire off location requests
     * to both the NETWORK_PROVIDER & GPS_PROVIDER and stop listening for updates as soon as
     * the first location is provided.
     */
    private void getLocationAndLoadForecast() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission();
        } else {
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ForecastRepository.getInstance().loadForecast(location);
                    getLocationManager().removeUpdates(this);
                }
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {}
                @Override
                public void onProviderEnabled(String provider) {}
                @Override
                public void onProviderDisabled(String provider) {}
            };
            getLocationManager().requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, locationListener);
            getLocationManager().requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0, locationListener);
        }
    }

    /**
     *
     */
    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
    }

    /**
     * Subscribe and Listen for EventBus signals from ForecastRepository.
     * if success then Forecast object is cached in memory and we can display Forecast Fragment.
     *
     * @param success Boolean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onForecastEvent(Boolean success) {
        if (success) {
            displayForecastFragment();
        } else {
            displayErrorScreen(FORECAST_REQUEST_FAILURE);
        }
    }

    /**
     * Display appropriate error messages to the user with actions to recover these errors.
     *
     * @param error ErrorCode representing the error that has occurred.
     */
    private void displayErrorScreen(ErrorCode error) {
        switch (error) {
            case NO_CONNECTIVITY:
                errorIconIv.setImageResource(R.drawable.ic_no_connectivity);
                errorMessageTv.setText(getResources().getString(R.string.no_connectivity_error_message));
                errorBtn.setText(getResources().getString(R.string.retry_btn_action));
                errorBtn.setOnClickListener(view -> {
                    showLoadingIndicator();
                    setDailyTempsActivity();
                });
                showErrorLayout();
                break;
            case LOCATION_PERMISSION_DENIED:
                errorIconIv.setImageResource(R.drawable.ic_location_error);
                errorMessageTv.setText(getResources().getString(R.string.location_permission_denied_error_message));
                errorBtn.setText(getResources().getString(R.string.permission_denied_btn_action));
                errorBtn.setOnClickListener(view -> {
                    showLoadingIndicator();
                    requestLocationPermission();
                });
                showErrorLayout();
                break;
            case FORECAST_REQUEST_FAILURE:
                errorIconIv.setImageResource(R.drawable.ic_no_connectivity);
                errorMessageTv.setText(getResources().getString(R.string.forecast_request_failure_error_message));
                errorBtn.setText(getResources().getString(R.string.retry_btn_action));
                errorBtn.setOnClickListener(view -> {
                    showLoadingIndicator();
                    setDailyTempsActivity();
                });
                showErrorLayout();
                break;
        }
    }

    /**
     * @param requestCode  int
     * @param permissions  String[]
     * @param grantResults int[]
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocationAndLoadForecast();
            } else {
                displayErrorScreen(LOCATION_PERMISSION_DENIED);
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
        if (id == R.id.action_refresh) {
            showLoadingIndicator();
            resetForecastRepository();
            setDailyTempsActivity();
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Register EventBus to the lifecycle of this activity.
     */
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * Unregister EventBus from the lifecycle of this activity.
     */
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     *
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    ////    HELPER METHODS    ////

    /**
     *
     */
    private LocationManager getLocationManager() {
        return (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     *
     */
    private void showFragmentContainer() {
        loadingIndicator.setVisibility(GONE);
        errorLayout.setVisibility(GONE);
        fragmentContainer.setVisibility(VISIBLE);
    }

    /**
     *
     */
    private void showLoadingIndicator() {
        fragmentContainer.setVisibility(GONE);
        errorLayout.setVisibility(GONE);
        loadingIndicator.setVisibility(VISIBLE);
    }

    /**
     *
     */
    private void showErrorLayout() {
        loadingIndicator.setVisibility(GONE);
        fragmentContainer.setVisibility(GONE);
        errorLayout.setVisibility(VISIBLE);
    }

    /**
     *
     */
    private Forecast getForecast() {
        return ForecastRepository.getInstance().getForecast();
    }

    /**
     *
     */
    private void resetForecastRepository() {
        ForecastRepository.getInstance().reset();
    }

}
