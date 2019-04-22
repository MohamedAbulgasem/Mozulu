package com.mohamedabulgasem.mozulu.ui;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.ForecastRepository;
import com.mohamedabulgasem.mozulu.data.model.Currently;
import com.mohamedabulgasem.mozulu.data.model.Forecast;
import com.mohamedabulgasem.mozulu.ui.adapters.DailyRvAdapter;
import com.mohamedabulgasem.mozulu.ui.adapters.HourlyRvAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.DAILY_POSITION;
import static com.mohamedabulgasem.mozulu.utils.GeocodeUtils.getAddressName;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.getDisplayTemperature;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.setWeatherIcon;

/**
 * Created by Mohamed Abulgasem on 2019/04/22.
 */
public class ForecastFragment extends Fragment
        implements DailyRvAdapter.DailyRvAdapterOnClickHandler {

    @BindView(R.id.currently_address_tv)
    TextView currentlyAddressNameTv;

    @BindView(R.id.currently_temperature_tv)
    TextView currentlyTempTv;

    @BindView(R.id.currently_icon_iv)
    ImageView currentlyIconIv;

    @BindView(R.id.currently_summary_tv)
    TextView currentlySummaryTv;

    @BindView(R.id.daily_rv)
    RecyclerView dailyRv;

    @BindView(R.id.hourly_rv)
    RecyclerView hourlyRv;

    public static final String TAG = ForecastFragment.class.getSimpleName();

    private Context context;


    /**
     * Required empty public constructor
     */
    public ForecastFragment() {}

    /**
     * @param context Context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * @param inflater           LayoutInflater
     * @param parent             ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * @param view               View
     * @param savedInstanceState Bundle
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        setCurrentlyLayout();
        setHourlyRecyclerView();
        setDailyRecyclerView();
    }

    /**
     *
     */
    private void setCurrentlyLayout() {
        Currently currently = getForecast().getCurrently();
        String addressName = getAddressName(context.getApplicationContext(), getLocation());
        if (addressName != null) {
            currentlyAddressNameTv.setText(addressName);
        } else {
            currentlyAddressNameTv.setVisibility(GONE);
        }
        String temperature = getDisplayTemperature(currently.getTemperature());
        currentlyTempTv.setText(temperature);
        setWeatherIcon(currentlyIconIv, currently.getIcon().trim());
        currentlySummaryTv.setText(currently.getSummary());
    }

    /**
     *
     */
    private void setHourlyRecyclerView() {
        HourlyRvAdapter adapter = new HourlyRvAdapter(getForecast().getHourly().getData());
        hourlyRv.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.HORIZONTAL, false));
        hourlyRv.setAdapter(adapter);
    }

    /**
     *
     */
    private void setDailyRecyclerView() {
        DailyRvAdapter adapter = new DailyRvAdapter(getForecast().getDaily().getData(),
                this);
        dailyRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dailyRv.setAdapter(adapter);
    }

    /**
     * DailyRecyclerView OnClickHandler
     *
     * @param position int
     */
    @Override
    public void onItemClick(int position) {
        Intent weatherDetailsIntent = new Intent(context, WeatherDetailsActivity.class);
        weatherDetailsIntent.putExtra(DAILY_POSITION, position);
        startActivity(weatherDetailsIntent);
    }

    /**
     *
     */
    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    ////    HELPER METHODS    ////

    /**
     *
     */
    private Forecast getForecast() {
        return ForecastRepository.getInstance().getForecast();
    }

    /**
     *
     */
    private Location getLocation() {
        return ForecastRepository.getInstance().getLocation();
    }

}
