package com.mohamedabulgasem.mozulu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.model.WeatherDetail;

import java.util.List;

/**
 * Created by Mohamed Abulgasem on 2019/04/22.
 */
public class WeatherDetailsAdapter extends RecyclerView.Adapter<WeatherDetailsAdapter.WeatherDetailsViewHolder> {

    private List<WeatherDetail> weatherDetails;

    /**
     * @param weatherDetails List<WeatherDetail>
     */
    public WeatherDetailsAdapter(List<WeatherDetail> weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    /**
     * @param parent ViewGroup
     * @param viewType int
     * @return WeatherDetailsViewHolder
     */
    @NonNull
    @Override
    public WeatherDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View individualView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_details_list_item, parent, false);
        return new WeatherDetailsViewHolder(individualView);
    }

    /**
     * @param holder WeatherDetailsViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull WeatherDetailsViewHolder holder, int position) {
        holder.iconIv.setImageResource(weatherDetails.get(position).getIcon());
        holder.nameTv.setText(weatherDetails.get(position).getName());
        holder.valueTv.setText(weatherDetails.get(position).getValue());
    }

    /**
     * @return int
     */
    @Override
    public int getItemCount() {
        if (weatherDetails == null) {
            return 0;
        }
        return weatherDetails.size();
    }

    /**
     *
     */
    public class WeatherDetailsViewHolder extends RecyclerView.ViewHolder {

        final ImageView iconIv;
        final TextView nameTv;
        final TextView valueTv;

        /**
         * @param itemView View
         */
        WeatherDetailsViewHolder(View itemView) {
            super(itemView);
            iconIv = itemView.findViewById(R.id.weather_item_icon_iv);
            nameTv = itemView.findViewById(R.id.weather_item_name_tv);
            valueTv = itemView.findViewById(R.id.weather_item_value_tv);
        }

    }

}
