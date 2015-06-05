package com.tripoin.tripoin_util.gps.impl;

import android.content.Context;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.tripoin.tripoin_util.gps.IGPSInformation;

import java.io.IOException;
import java.util.List;
import java.util.Locale;



/**
 * Created by Achmad Fauzi on 5/29/2015 : 3:37 PM.
 * mail : achmad.fauzi@sigma.co.id
 */
public class GPSInformationImpl implements IGPSInformation {

    protected LocationManager locationManager;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;
    protected Location location = null;
    protected double latitude;
    protected double longitude;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    private MQALocationListener mqaLocationListener;
    private Context context;

    public GPSInformationImpl(LocationManager locationManager, Context context) {
        this.locationManager = locationManager;
        this.context = context;
        mqaLocationListener = new MQALocationListener();
    }

    @Override
    public Location getLocation() {
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (!isGPSEnabled && !isNetworkEnabled){
            // no network provider is enabled
        }else{
            this.canGetLocation = true;
            if (isGPSEnabled){
                if (location == null){
                    locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mqaLocationListener);
                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        updateGPSCoordinates();
                    }
                }
            }
            else if (isNetworkEnabled){
                if( location == null ){
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, mqaLocationListener);
                }
                if (locationManager != null){
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    updateGPSCoordinates();
                }
            }
        }
        return null;
    }

    @Override
    public void stopUsingGPS() {
        if (locationManager != null){
            locationManager.removeUpdates((android.location.LocationListener) this);
        }
    }

    @Override
    public boolean isGetLocation() {
        return this.canGetLocation;
    }

    @Override
    public void showGPSSettingAlert() {
    }

    @Override
    public float getGpsAccuracy() {
        return mqaLocationListener.getGpsAccuracy();
    }

    @Override
    public void updateGPSCoordinates() {
        if (location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
    }

    @Override
    public double getLatitude() {
        if (location != null){
            latitude = location.getLatitude();
        }
        return latitude;
    }

    @Override
    public double getLongitude() {
        if (location != null){
            longitude = location.getLongitude();
        }
        return longitude;
    }

    @Override
    public List<Address> getGeocoderAddress() {
        List<Address> addresses = null;
        Log.d("geocoderaddress", "1");
        if (location != null){
            Log.d("geocoderaddress", "2");
            Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
            Log.d("geocoderaddress", "3");
            try{
                Log.d("geocoderaddress", "4");
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                Log.d("geocoderaddress", "5");
            }
            catch (IOException e){
                Log.e("Error : Geocoder", "Impossible to connect to Geocoder", e);
            }
        }
        return addresses;
    }

    @Override
    public String getAddressLine() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0){
            Address address = addresses.get(0);

            return address.getAddressLine(0);
        }else{
            return null;
        }
    }

    @Override
    public String getLocality() {
        List<Address> addresses = getGeocoderAddress();
        Log.d("getlocality", "1");
        if (addresses != null && addresses.size() > 0){
            Log.d("getlocality", "2");
            Address address = addresses.get(0);
            Log.d("getlocality", "3");
            return address.getLocality();
        }else{
            Log.d("getlocality", "5");
            return null;
        }
    }

    @Override
    public String getPostalCode() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0){
            Address address = addresses.get(0);

            return address.getPostalCode();
        }else{
            return null;
        }
    }

    @Override
    public String getCountryName() {
        List<Address> addresses = getGeocoderAddress();
        if (addresses != null && addresses.size() > 0){
            Address address = addresses.get(0);
            return address.getCountryName();
        }else{
            return null;
        }
    }
}
