package com.mohamedabulgasem.mozulu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.model.datapoints.HourlyDataPoint;

import java.util.List;

import static com.mohamedabulgasem.mozulu.utils.DateUtils.getHour;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.getDisplayTemperature;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.setWeatherIcon;

/**
 * Created by Mohamed Abulgasem on 2019/04/21.
 */
public class HourlyRvAdapter extends RecyclerView.Adapter<HourlyRvAdapter.HourlyRvViewHolder> {

    private List<HourlyDataPoint> hourlyList;

    /**
     * @param dailyList List<HourlyDataPoint>
     */
    public HourlyRvAdapter(List<HourlyDataPoint> dailyList) {
        this.hourlyList = dailyList;
    }

    /**
     * @param parent ViewGroup
     * @param viewType int
     * @return HourlyRvViewHolder
     */
    @NonNull
    @Override
    public HourlyRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View individualView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item, parent, false);
        return new HourlyRvViewHolder(individualView);
    }

    /**
     * @param holder HourlyRvViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull HourlyRvViewHolder holder, int position) {
        holder.timeTv.setText(getHour(hourlyList.get(position).getTime()));
        setWeatherIcon(holder.iconIv, hourlyList.get(position).getIcon());
        holder.tempTv.setText(getDisplayTemperature(hourlyList.get(position).getTemperature()));
    }

    /**
     * @return int
     */
    @Override
    public int getItemCount() {
        if (hourlyList == null) {
            return 0;
        }
        return hourlyList.size();
    }

    /**
     *
     */
    public class HourlyRvViewHolder extends RecyclerView.ViewHolder {

        final TextView timeTv;
        final ImageView iconIv;
        final TextView tempTv;

        /**
         * @param itemView View
         */
        HourlyRvViewHolder(View itemView) {
            super(itemView);
            timeTv = itemView.findViewById(R.id.hourly_time_tv);
            iconIv = itemView.findViewById(R.id.hourly_icon_iv);
            tempTv = itemView.findViewById(R.id.hourly_temperature_tv);
        }

    }

}
