package com.example.musfiqrahman.waitlist.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by T00533766 on 1/15/2018.
 */

public class GuestInfoDatabaseHelper extends SQLiteOpenHelper {

    private static final int GUEST_INFO_DATABASE_VERSION = 1;

    public GuestInfoDatabaseHelper(Context context) {
        super(context, GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME, null, GUEST_INFO_DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(GuestInfoDatabaseContract.CREATE_GUEST_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(GuestInfoDatabaseContract.DELETE_GUEST_INFO_TABLE);
        onCreate(sqLiteDatabase);
    }
}
