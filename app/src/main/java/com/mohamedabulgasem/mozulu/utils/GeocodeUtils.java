package com.mohamedabulgasem.mozulu.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mohamed Abulgasem on 2019/04/19.
 */
public final class GeocodeUtils {

    /**
     * This utility class is not instantiable
     */
    private GeocodeUtils() {}

    public static String getAddressName(Context context, Location location) {
        String addressName = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder
                    .getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0) {
                /*
                if full address is available set it to the second
                and third parts of the address which describe the area.
                Ignoring the first part of the address as it often describes
                a very precise inaccurate house or apartment name etc address.
                */
                String fullAddress = addresses.get(0).getAddressLine(0);
                if (!fullAddress.isEmpty()) {
                    String[] addressParts = fullAddress.split(",");
                    if (addressParts.length >= 3) {
                        addressName = addressParts[1].trim() + ", " + addressParts[2].trim();
                    } else if (addressParts.length == 2) {
                        addressName = addressParts[1].trim();
                    }
                }
                /*
                else set it to locality if available.
                 */
                else if (addresses.get(0).getLocality() != null) {
                    addressName = addresses.get(0).getLocality();
                }
            }
        } catch (IOException e) {
            Log.e("TAG", e.getMessage());
        }
        return addressName;
    }

}
