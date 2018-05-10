package com.example.t00533766.locationservices;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by T00533766 on 3/12/2018.
 */

public class LocationViewModel extends AndroidViewModel {

    private LiveData<Location> locationLiveData;
    private final String TAG  = "VIEW MODEL TAG";

    public LocationViewModel(@NonNull Application application) {
        super(application);
        locationLiveData = LocationLiveData.getInstance(application);
    }

    public LiveData<Location> getLocation (){
        Log.d(TAG, "getLocation: "+locationLiveData.getValue());
        return locationLiveData;
    }

}

