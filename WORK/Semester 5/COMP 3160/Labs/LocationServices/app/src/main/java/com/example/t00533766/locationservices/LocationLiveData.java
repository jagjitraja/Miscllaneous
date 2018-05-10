package com.example.t00533766.locationservices;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by T00533766 on 3/12/2018.
 */

public class LocationLiveData extends LiveData<Location> {

    private static LocationLiveData locationLiveDataInstance;
    private final int LOCATION_REQUEST_CODE = 3;
    private Context context;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private static final String TAG = "LOCATION LIVE DATA";

    private FusedLocationProviderClient fusedLocationProviderClient;

    public static LocationLiveData getInstance(Context context) {

        if (locationLiveDataInstance == null) {
            locationLiveDataInstance = new LocationLiveData(context);
            locationLiveDataInstance.context = context;
        }

        return locationLiveDataInstance;
    }

    private LocationLiveData(Context context) {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult == null) {
                    return;
                }
                Log.d(TAG, "onLocationResult: ");

                if(locationResult.getLastLocation()!=null)
                    LocationLiveData.super.setValue(locationResult.getLastLocation());

            }
        };

        locationRequest = new LocationRequest().setInterval(500).setMaxWaitTime(1000).setFastestInterval(1000);

    }



    //WHENEVER THERE IS AN OBSERVER FOR THIS LIVE DATA, REGISTER FOR LOCATION UPDATES WITH CALLBACK
    @Override
    protected void onActive() {
        super.onActive();

        Log.d(TAG, "onActive: --------------------------");

        try {


            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
        catch (SecurityException e){

        }

    }
    

    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d(TAG, "onInactive: ");
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
}
