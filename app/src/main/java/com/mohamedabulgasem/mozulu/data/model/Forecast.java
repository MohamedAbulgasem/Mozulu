package com.mohamedabulgasem.mozulu.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the Dark Sky API Forecast response
 *
 * Created by Mohamed Abulgasem on 2019/04/16.
 */
public class Forecast {

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("currently")
    private Currently currently;

    @SerializedName("hourly")
    private Hourly hourly;

    @SerializedName("daily")
    private Daily daily;

    /**
     * Non-mandatory default constructor used by GSON
     * to instantiate this object and set its fields via reflection.
     *
     * if not present GSON would still create a default no-args constructor
     * and use that - which is the case with the other domain objects in this project.
     */
    public Forecast() {}

    /**
     * The requested latitude.
     *
     * @return double
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * The requested longitude.
     *
     * @return double
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * The IANA timezone name for the requested location. This is used for text
     * summaries and for determining when hourly and daily data block objects begin.
     *
     * @return String
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * A data point containing the current weather conditions at the requested location.
     *
     * @return Currently
     */
    public Currently getCurrently() {
        return currently;
    }

    /**
     * A data block containing the weather conditions hour-by-hour for the next two days.
     *
     * @return Hourly
     */
    public Hourly getHourly() {
        return hourly;
    }

    /**
     * A data block containing the weather conditions day-by-day for the next week.
     *
     * @return Daily
     */
    public Daily getDaily() {
        return daily;
    }

}
