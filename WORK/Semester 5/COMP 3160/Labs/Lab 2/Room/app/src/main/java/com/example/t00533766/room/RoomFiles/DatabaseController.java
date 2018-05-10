package com.example.t00533766.room.RoomFiles;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import com.example.t00533766.room.RoomFiles.GuestInfo;
import com.example.t00533766.room.RoomFiles.GuestInfoDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by T00533766 on 1/17/2018.
 */

public class DatabaseController {
    private GuestInfoDatabase guestInfoDatabase;
    private Context context;
    private List<GuestInfo> guestInfos;
    private GuestInfoViewModel guestInfoViewModel;
    private Activity activity;

    public DatabaseController(Context context, FragmentActivity activity){
        this.context = context;
        guestInfoViewModel = ViewModelProviders.of(activity).get(GuestInfoViewModel.class);
    }


    public List<GuestInfo> selectGuestInfos(){
        SelectAsyncTask selectAsyncTask = new SelectAsyncTask();
        try {
            guestInfos = selectAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return guestInfos;
    }

    public void deleteGuestInfo(GuestInfo guestInfo){
        DeleteAsyncTask deleteAsyncTask = new DeleteAsyncTask();
        deleteAsyncTask.execute(guestInfo);
    }

    public void updateGuestInfo(GuestInfo guestInfo){
        UpdateAsyncTask updateAsyncTask = new UpdateAsyncTask();
        updateAsyncTask.execute(guestInfo);
    }
    public void inserGuestInfo(GuestInfo guestInfo){
        InsertAsyncTask selectAsyncTask = new InsertAsyncTask();
        selectAsyncTask.execute(guestInfo);
    }

    public class InsertAsyncTask extends AsyncTask<GuestInfo,Integer,Void>{

        @Override
        protected Void doInBackground(GuestInfo... guests) {

            //guestInfoDatabase = GuestInfoDatabase.getGuestInfoDatabaseInstance(context);
            //guestInfoDatabase.guestInfoDAO().insertGuestInfo(guests[0]);
            guestInfoViewModel.insertGuestInfo(guests[0]);
            return null;
        }
    }
    public class DeleteAsyncTask extends AsyncTask<GuestInfo,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(GuestInfo... guestInfos) {

            //guestInfoDatabase = GuestInfoDatabase.getGuestInfoDatabaseInstance(context);
            //guestInfoDatabase.guestInfoDAO().deleteGuestInfo(guestInfos[0]);

            return null;
        }
    }
    public class UpdateAsyncTask extends AsyncTask<GuestInfo,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(GuestInfo... guestInfos) {

            guestInfoDatabase = GuestInfoDatabase.getGuestInfoDatabaseInstance(context);
            guestInfoDatabase.guestInfoDAO().updateGuestInfo(guestInfos[0]);
            return null;
        }
    }
    public class SelectAsyncTask extends AsyncTask<Void, Integer, List<GuestInfo>> {

        @Override
        protected List<GuestInfo> doInBackground(Void...voids) {

            //guestInfoDatabase = GuestInfoDatabase.getGuestInfoDatabaseInstance(context);
            //guestInfos =  guestInfoDatabase.guestInfoDAO().getAllGuestsInfo();
            guestInfos = guestInfoViewModel.getGuestInfoLiveData().getValue();
            return guestInfos;
        }

        @Override
        protected void onPostExecute(List<GuestInfo> guests) {
            super.onPostExecute(guestInfos);
            guestInfos = guests;
        }
    }
}
