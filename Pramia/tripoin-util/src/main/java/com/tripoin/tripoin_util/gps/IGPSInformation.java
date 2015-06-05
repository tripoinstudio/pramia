package com.tripoin.tripoin_util.gps;

import android.location.Address;
import android.location.Location;

import java.util.List;

/**
 * Created by Achmad Fauzi on 5/29/2015 : 3:38 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public interface IGPSInformation {

    public void stopUsingGPS();

    public boolean isGetLocation();

    public void showGPSSettingAlert();

    public float getGpsAccuracy();

    public void updateGPSCoordinates();

    public Location getLocation();

    public double getLatitude();

    public double getLongitude();

    public List<Address> getGeocoderAddress();

    public String getAddressLine();

    public String getLocality();

    public String getPostalCode();

    public String getCountryName();
}
