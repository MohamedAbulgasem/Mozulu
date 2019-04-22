package com.mohamedabulgasem.mozulu.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mohamedabulgasem.mozulu.R;
import com.mohamedabulgasem.mozulu.data.model.datapoints.DailyDataPoint;

import java.util.List;

import static com.mohamedabulgasem.mozulu.utils.DateUtils.getDay;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.getDisplayTemperature;
import static com.mohamedabulgasem.mozulu.utils.UiUtils.setWeatherIcon;

/**
 * Created by Mohamed Abulgasem on 2019/04/21.
 */
public class DailyRvAdapter extends RecyclerView.Adapter<DailyRvAdapter.DailyRvViewHolder> {

    private List<DailyDataPoint> dailyList;
    private final DailyRvAdapterOnClickHandler itemClickHandler;

    /**
     * @param dailyList List<DailyDataPoint>
     * @param itemClickHandler DailyRvAdapterOnClickHandler
     */
    public DailyRvAdapter(List<DailyDataPoint> dailyList, DailyRvAdapterOnClickHandler itemClickHandler) {
        this.dailyList = dailyList;
        this.itemClickHandler = itemClickHandler;
    }

    /**
     * @param parent ViewGroup
     * @param viewType int
     * @return DailyRvViewHolder
     */
    @NonNull
    @Override
    public DailyRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View individualView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_list_item, parent, false);
        return new DailyRvViewHolder(individualView);
    }

    /**
     * @param holder DailyRvViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull DailyRvViewHolder holder, int position) {
        holder.dayNameTv.setText(getDay(dailyList.get(position).getTime()));
        setWeatherIcon(holder.iconIv, dailyList.get(position).getIcon());
        holder.maxTempTv.setText(getDisplayTemperature(dailyList.get(position).getTemperatureMax()));
        holder.minTempTv.setText(getDisplayTemperature(dailyList.get(position).getTemperatureMin()));
    }

    /**
     * @return int
     */
    @Override
    public int getItemCount() {
        if (dailyList == null) {
            return 0;
        }
        return dailyList.size();
    }

    /**
     *
     */
    public class DailyRvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView dayNameTv;
        final ImageView iconIv;
        final TextView maxTempTv;
        final TextView minTempTv;

        /**
         * @param itemView View
         */
        DailyRvViewHolder(View itemView) {
            super(itemView);
            dayNameTv = itemView.findViewById(R.id.item_day_tv);
            iconIv = itemView.findViewById(R.id.item_icon_iv);
            maxTempTv = itemView.findViewById(R.id.item_max_temp_tv);
            minTempTv = itemView.findViewById(R.id.item_min_temp_tv);
            itemView.setOnClickListener(this);
        }

        /**
         * @param v View
         */
        @Override
        public void onClick(View v) {
            itemClickHandler.onItemClick(getAdapterPosition());
        }

    }

    /**
     *
     */
    public interface DailyRvAdapterOnClickHandler {
        void onItemClick(int position);
    }

}
