package com.example.t00533766.room.RoomFiles;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

import com.example.t00533766.room.GuestInfoProvider.GuestInfoContract;

/**
 * Created by T00533766 on 1/17/2018.
 */

@Database(entities = {GuestInfo.class},version = 1)
public abstract class GuestInfoDatabase extends RoomDatabase {
    public abstract GuestInfoDAO guestInfoDAO();
    private static GuestInfoDatabase guestInfoDatabase;

    public static GuestInfoDatabase getGuestInfoDatabaseInstance(Context context){
        if (guestInfoDatabase ==null){
            guestInfoDatabase = Room.databaseBuilder(context,GuestInfoDatabase.class,
                    GuestInfoContract.DATABASE_NAME).build();
        }
        return guestInfoDatabase;
    }

}
