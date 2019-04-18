package com.mohamedabulgasem.mozulu.data.model.datapoints;

import com.google.gson.annotations.SerializedName;

/**
 * A data block containing the daily weather conditions.
 *
 * Created by Mohamed Abulgasem on 2019/04/17.
 */
public class DailyDataPoint extends DataPoint {

    @SerializedName("sunriseTime")
    private long sunriseTime;

    @SerializedName("sunsetTime")
    private long sunsetTime;

    @SerializedName("temperatureMin")
    private double temperatureMin;

    @SerializedName("temperatureMinTime")
    private long temperatureMinTime;

    @SerializedName("temperatureMax")
    private double temperatureMax;

    @SerializedName("temperatureMaxTime")
    private long temperatureMaxTime;

    /**
     * The UNIX time of when the sun will rise during a given day.
     *
     * @return long
     */
    public long getSunriseTime() {
        return sunriseTime;
    }

    /**
     * The UNIX time of when the sun will set during a given day.
     *
     * @return long
     */
    public long getSunsetTime() {
        return sunsetTime;
    }

    /**
     * The minimum temperature during a given date.
     *
     * @return double
     */
    public double getTemperatureMin() {
        return temperatureMin;
    }

    /**
     * The UNIX time representing when the minimum temperature during a given date occurs.
     *
     * @return long
     */
    public long getTemperatureMinTime() {
        return temperatureMinTime;
    }

    /**
     * The maximum temperature during a given date.
     *
     * @return double
     */
    public double getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * The UNIX time representing when the maximum temperature during a given date occurs.
     *
     * @return long
     */
    public long getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

}
