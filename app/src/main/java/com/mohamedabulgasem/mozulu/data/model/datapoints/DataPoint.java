package com.mohamedabulgasem.mozulu.data.model.datapoints;

import com.google.gson.annotations.SerializedName;

/**
 * This abstract class represents common properties that are shared amongst
 * the different weather type data points in the Forecast response
 *
 * Created by Mohamed Abulgasem on 2019/04/17.
 */
public abstract class DataPoint {

    @SerializedName("time")
    private long time;

    @SerializedName("summary")
    private String summary;

    @SerializedName("icon")
    private String icon;

    @SerializedName("precipProbability")
    private double precipProbability;

    @SerializedName("precipType")
    private String precipType;

    @SerializedName("humidity")
    private double humidity;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("windSpeed")
    private double windSpeed;

    @SerializedName("windBearing")
    private int windBearing;

    @SerializedName("cloudCover")
    private double cloudCover;

    @SerializedName("visibility")
    private double visibility;

    /**
     * The UNIX time at which this data point begins.
     *
     * @return long
     */
    public long getTime() {
        return time;
    }

    /**
     * A human-readable text summary of this data point.
     *
     * @return String
     */
    public String getSummary() {
        return summary;
    }

    /**
     * A machine-readable text summary of this data point,
     * suitable for selecting an icon for display.
     * If defined, this property will have one of the following values:
     * clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy,
     * partly-cloudy-day, or partly-cloudy-night.
     *
     * @return String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * The probability of precipitation occurring, between 0 and 1, inclusive.
     *
     * @return double
     */
    public double getPrecipProbability() {
        return precipProbability;
    }

    /**
     * The type of precipitation occurring at the given time.
     * If defined, this property will have one of the following values:
     * "rain", "snow", or "sleet"
     *
     * @return String
     */
    public String getPrecipType() {
        return precipType;
    }

    /**
     * The relative humidity, between 0 and 1, inclusive.
     *
     * @return double
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * The sea-level air pressure in millibars.
     *
     * @return double
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * The wind speed in kilometers per hour.
     *
     * @return double
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * The direction that the wind is coming from in degrees, with true north at 0°
     * and progressing clockwise. (If windSpeed is zero, then this value will not be defined.)
     *
     * @return int
     */
    public int getWindBearing() {
        return windBearing;
    }

    /**
     * The percentage of sky occluded by clouds, between 0 and 1, inclusive.
     *
     * @return double
     */
    public double getCloudCover() {
        return cloudCover;
    }

    /**
     * The average visibility in miles, capped at 10 miles.
     *
     * @return double
     */
    public double getVisibility() {
        return visibility;
    }

}
