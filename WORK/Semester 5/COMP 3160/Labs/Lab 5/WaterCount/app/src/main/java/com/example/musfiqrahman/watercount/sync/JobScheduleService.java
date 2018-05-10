package com.example.musfiqrahman.watercount.sync;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;

import com.example.musfiqrahman.watercount.utilities.NotificationUtilities;

/**
 * Created by T00533766 on 2/26/2018.
 */

public class JobScheduleService extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        NotificationUtilities.createNotification(getApplicationContext());
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
