package com.mohamedabulgasem.mozulu.data.model;

/**
 * Created by Mohamed Abulgasem on 2019/04/22.
 */
public class WeatherDetail {

    private int icon;
    private String name;
    private String value;

    /**
     * @param icon int
     * @param name String
     * @param value String
     */
    public WeatherDetail(int icon, String name, String value) {
        this.icon = icon;
        this.name = name;
        this.value = value;
    }

    /**
     * @return String
     */
    public int getIcon() {
        return icon;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return String
     */
    public String getValue() {
        return value;
    }

}
