package com.tripoin.tripoin_util.gps.impl;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Achmad Fauzi on 5/29/2015 : 4:28 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class MQALocationListener implements LocationListener {

    private float gpsAccuracy;

    @Override
    public void onLocationChanged(Location location) {
        if ( location != null ){
            gpsAccuracy = location.getAccuracy();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public float getGpsAccuracy() {
        return gpsAccuracy;
    }
}
