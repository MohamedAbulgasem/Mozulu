package com.mohamedabulgasem.mozulu.utils;

import android.widget.ImageView;

import com.mohamedabulgasem.mozulu.R;

import static com.mohamedabulgasem.mozulu.utils.AppConstants.CLEAR_DAY;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.CLEAR_NIGHT;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.CLOUDY;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.FOG;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.PARTLY_CLOUDY_DAY;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.PARTLY_CLOUDY_NIGHT;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.RAIN;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.SLEET;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.SNOW;
import static com.mohamedabulgasem.mozulu.utils.AppConstants.WIND;

/**
 * Created by Mohamed Abulgasem on 2019/04/21.
 */
public final class UiUtils {

    /**
     * This utility class is not instantiable
     */
    private UiUtils() {}

    /**
     * @param icon ImageView
     * @param iconName String
     */
    public static void setWeatherIcon(ImageView icon, String iconName) {
        switch (iconName) {
            case CLEAR_DAY:
                icon.setImageResource(R.drawable.clear_day);
                break;
            case CLEAR_NIGHT:
                icon.setImageResource(R.drawable.clear_night);
                break;
            case RAIN:
                icon.setImageResource(R.drawable.rain);
                break;
            case SNOW:
                icon.setImageResource(R.drawable.snow);
                break;
            case SLEET:
                icon.setImageResource(R.drawable.sleet);
                break;
            case WIND:
                icon.setImageResource(R.drawable.wind);
                break;
            case FOG:
                icon.setImageResource(R.drawable.fog);
                break;
            case CLOUDY:
                icon.setImageResource(R.drawable.cloudy);
                break;
            case PARTLY_CLOUDY_DAY:
                icon.setImageResource(R.drawable.partly_cloudy_day);
                break;
            case PARTLY_CLOUDY_NIGHT:
                icon.setImageResource(R.drawable.partly_cloudy_night);
                break;
            default:
                icon.setImageResource(R.drawable.default_weather_icon);
                break;
        }
    }

    /**
     * @param temp double
     * @return String
     */
    public static String getDisplayTemperature(double temp) {
        return  Math.round(temp) + "°";
    }

}
