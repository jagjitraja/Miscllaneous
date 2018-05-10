package com.example.t00533766.room.RoomFiles;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by T00533766 on 1/23/2018.
 */

public class GuestInfoDBRepository{

    private GuestInfoDAO guestInfoDAO;
    private LiveData<List<GuestInfo>> guestInfoLiveData;

    public GuestInfoDBRepository(Context context){
        guestInfoDAO = GuestInfoDatabase.getGuestInfoDatabaseInstance(context).guestInfoDAO();
    }

    public LiveData<List<GuestInfo>> getAllGuestInfo(){
        guestInfoLiveData = new MutableLiveData<>();
        SelectAsyncTask asyncTask = new SelectAsyncTask(guestInfoDAO);
        try {
            guestInfoLiveData = asyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return guestInfoLiveData;
    }

    public void insertGuestInfo(GuestInfo info){
        new InsertAsyncTask(guestInfoDAO).execute(info);
    }


    public class InsertAsyncTask extends AsyncTask<GuestInfo, Integer, Void> {
        private GuestInfoDAO guestInfoDAO;
        public InsertAsyncTask(GuestInfoDAO guestInfoDAO){
            this.guestInfoDAO = guestInfoDAO;
        }
        @Override
        protected Void doInBackground(GuestInfo... guests) {
            guestInfoDAO.insertGuestInfo(guests[0]);
            return null;
        }
    }

    public class DeleteAsyncTask extends AsyncTask<GuestInfo, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(GuestInfo... guestInfos) {

            return null;
        }
    }

    public class SelectAsyncTask extends AsyncTask<Void, Integer, LiveData<List<GuestInfo>>> {

        private final GuestInfoDAO guestInfoDAO;

        public SelectAsyncTask(GuestInfoDAO infoDAO){
            this.guestInfoDAO = infoDAO;
        }
        @Override
        protected LiveData<List<GuestInfo>> doInBackground(Void... voids) {

            return guestInfoDAO.getAllGuestsInfo();
        }

    }
}
