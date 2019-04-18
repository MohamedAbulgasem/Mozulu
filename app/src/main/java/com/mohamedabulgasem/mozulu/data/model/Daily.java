package com.mohamedabulgasem.mozulu.data.model;

import com.google.gson.annotations.SerializedName;
import com.mohamedabulgasem.mozulu.data.model.datapoints.DailyDataPoint;

import java.util.List;

/**
 * Created by Mohamed Abulgasem on 2019/04/17.
 */
public class Daily {

    @SerializedName("summary")
    private String summary;

    @SerializedName("icon")
    private String icon;

    @SerializedName("data")
    private List<DailyDataPoint> data;

    /**
     * A human-readable summary of this data block.
     *
     * @return String
     */
    public String getSummary() {
        return summary;
    }

    /**
     * A machine-readable text summary of this data block.
     * (May take on the same values as the iconproperty of data points.)
     *
     * @return String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * An array of data points, ordered by time, which together describe
     * the weather conditions at the requested location over time.
     *
     * @return List<DailyDataPoint>
     */
    public List<DailyDataPoint> getData() {
        return data;
    }

}
