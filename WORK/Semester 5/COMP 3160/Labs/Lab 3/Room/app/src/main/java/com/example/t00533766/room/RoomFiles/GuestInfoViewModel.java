package com.example.t00533766.room.RoomFiles;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by T00533766 on 1/23/2018.
 */

public class GuestInfoViewModel extends ViewModel {

    private GuestInfoDBRepository repository;

    public GuestInfoViewModel() {
        Log.d("======================", "GuestInfoViewModel: ");
    }

    public void createReporsitory(Context context){
        this.repository = new GuestInfoDBRepository(context);
    }

    public LiveData<List<GuestInfo>> getGuestInfoLiveData(){

        return repository.getAllGuestInfo();
    }

    public void insertGuestData(GuestInfo guestInfo){
        repository.insertGuestInfo(guestInfo);
    }

}
