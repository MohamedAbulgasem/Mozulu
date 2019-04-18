package com.mohamedabulgasem.mozulu.data.model.datapoints;

import com.google.gson.annotations.SerializedName;

/**
 * A data block containing the hourly weather conditions.
 *
 * Created by Mohamed Abulgasem on 2019/04/17.
 */
public class HourlyDataPoint extends DataPoint {

    @SerializedName("temperature")
    private double temperature;

    @SerializedName("apparentTemperature")
    private double apparentTemperature;

    /**
     * The apparent (or “feels like”) temperature in degrees.
     *
     * @return double
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * The air temperature in degrees.
     *
     * @return double
     */
    public double getApparentTemperature() {
        return apparentTemperature;
    }

}
