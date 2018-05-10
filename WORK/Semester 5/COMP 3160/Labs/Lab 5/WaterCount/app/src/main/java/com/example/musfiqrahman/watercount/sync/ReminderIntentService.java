package com.example.musfiqrahman.watercount.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.musfiqrahman.watercount.utilities.PreferenceUtilities;

/**
 * Created by T00533766 on 2/7/2018.
 */

public class ReminderIntentService extends IntentService {
    
    private final String TAG = "SERVICE -=-=-=-=-=-=-";

    public ReminderIntentService(){
        super(ReminderIntentService.class.getSimpleName());

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String action = intent.getAction();
        if(action.equals(ReminderTasks.ACTION_INCREMENT_WATER_COUNT)){

            ReminderTasks.executeTask(getApplicationContext(), action);
                Log.d("I AM RUNNING", "onHandleIntent: ");
//                synchronized (this) {
//                    try {
//                        this.wait(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }

        }

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
