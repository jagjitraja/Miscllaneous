package com.example.musfiqrahman.watercount.utilities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.example.musfiqrahman.watercount.MainActivity;
import com.example.musfiqrahman.watercount.R;
import com.example.musfiqrahman.watercount.sync.ReminderIntentService;
import com.example.musfiqrahman.watercount.sync.ReminderTasks;

/**
 * Created by T00533766 on 2/14/2018.
 */

public class NotificationUtilities {

    private final static String NOTIFICATION_CHANNEL_ID = " com.example.musfiqrahman.watercount.utilities";
    private final static String NOTIFICATION_CHANNEL_NAME ="Water Notification";
    private final static String NOTIFICATION_CHANNEL_DESCRIPTION = "Drink water*-*--*-*-*-*-*-*-*-*-*-*-*-*";
    private static final int NOTIFICATION_ID = 1;
    private static final int INTENT_ACTIVITY_ID = 1;

    @TargetApi(Build.VERSION_CODES.O)
    public static void createNotification(Context context){

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int NOTIFICATION_IMPORTANCE = NotificationManager.IMPORTANCE_HIGH;

        NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,NOTIFICATION_CHANNEL_NAME,NOTIFICATION_IMPORTANCE);
        channel.enableLights(true);
        channel.enableVibration(true);
        channel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);

        if (manager != null) {
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
                .setColor(Color.BLUE)
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_local_drink_black_24px))
                .setContentTitle("THIS IS A CONTENT TITLE")
                .setContentText("THIS IS CONTENT TEXT")
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setAutoCancel(true)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setContentIntent(getContentIntent(context))
                .addAction(drinkWaterAction(context))
                .addAction(ignoreAction(context));

        Notification notification = builder.build();

        manager.notify(NOTIFICATION_ID,notification);

    }

    private static NotificationCompat.Action ignoreAction(Context context) {

        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getService(context,INTENT_ACTIVITY_ID,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        return new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,"CANCEL",pendingIntent);
    }

    private static NotificationCompat.Action drinkWaterAction(Context context) {

        Intent intent = new Intent(context, ReminderIntentService.class);
        intent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);

        PendingIntent pendingIntent = PendingIntent.getService(context,INTENT_ACTIVITY_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Action(R.drawable.ic_drink_notification,"DRUNK IT!!!!!!",pendingIntent);
    }

    private static PendingIntent getContentIntent(Context context) {

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,INTENT_ACTIVITY_ID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }


}
