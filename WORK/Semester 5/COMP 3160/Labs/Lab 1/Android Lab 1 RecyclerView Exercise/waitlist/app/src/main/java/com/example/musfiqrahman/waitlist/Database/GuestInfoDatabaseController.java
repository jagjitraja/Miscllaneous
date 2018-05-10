package com.example.musfiqrahman.waitlist.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.musfiqrahman.waitlist.GuestInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by T00533766 on 1/15/2018.
 */

public class GuestInfoDatabaseController {

    private SQLiteDatabase guestInfoDatabase;
    private GuestInfoDatabaseHelper guestInfoDatabaseHelper;
    private Context context;


    public GuestInfoDatabaseController(Context context){
        this.context = context;
        guestInfoDatabaseHelper = new GuestInfoDatabaseHelper(context);
    }

    public long insertGuest(GuestInfo guest) {

        guestInfoDatabase = guestInfoDatabaseHelper.getWritableDatabase();
        ContentValues vals = new ContentValues();
        vals.put(GuestInfoDatabaseContract.GuestInfoTables.COLUMN_GUEST_NAME,guest.getGuestName());
        vals.put(GuestInfoDatabaseContract.GuestInfoTables.COLUMN_GUEST_PARTY_SIZE,guest.getPartySize());

        long rowID = guestInfoDatabase.insert(GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME,null,vals);
        guest.setDataBaseID(rowID);

        Log.d(guestInfoDatabase.toString(), "insertGuest: ");
        guestInfoDatabase.close();
        return rowID;
    }

    // Delete a row from the database, by rowId (primary key)
    public boolean deleteRow(GuestInfo guestInfo) {

        guestInfoDatabase = guestInfoDatabaseHelper.getWritableDatabase();
        String [] pars = {guestInfo.getDataBaseID()+""};
        int c = guestInfoDatabase.delete(GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME, GuestInfoDatabaseContract.GuestInfoTables._ID+" = ",pars);
        guestInfoDatabase.close();
        return c==0;
    }

    // Delete all records
    public void deleteAll() {
        guestInfoDatabase = guestInfoDatabaseHelper.getWritableDatabase();
        guestInfoDatabase.delete(GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME,GuestInfoDatabaseContract.GuestInfoTables._ID+"!= NULL",null);
        guestInfoDatabase.close();

    }

    // Return all rows in the database.
    public List<GuestInfo> getAllGuests() {

        guestInfoDatabase = guestInfoDatabaseHelper.getWritableDatabase();

        Cursor cursor = guestInfoDatabase.query(
                GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME,
               null,
                null,
                null,
                null,
                null,
                null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        List<GuestInfo> guestInfos = new ArrayList<>();

        if (cursor.getCount()==0){
            return null;
        }

        do{
            String name = cursor.getString(cursor.getColumnIndex(GuestInfoDatabaseContract.GuestInfoTables.COLUMN_GUEST_NAME));
            int partySize = cursor.getInt(cursor.getColumnIndex(GuestInfoDatabaseContract.GuestInfoTables.COLUMN_GUEST_PARTY_SIZE));
            long id =  cursor.getLong(cursor.getColumnIndex(GuestInfoDatabaseContract.GuestInfoTables._ID));
            GuestInfo info = new GuestInfo(name,partySize,id);
            guestInfos.add(info);

        }while (cursor.moveToNext());

        guestInfoDatabase.close();
        return guestInfos;
    }

    public GuestInfo getRow(long rowId) {
        guestInfoDatabase = guestInfoDatabaseHelper.getWritableDatabase();
        String select = GuestInfoDatabaseContract.GuestInfoTables._ID+"=";
        Cursor cursor = guestInfoDatabase.query(
                GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME,
                GuestInfoDatabaseContract.GuestInfoTables.GUEST_TABLE_COLUMNS,
                select,
                new String []{rowId+""},
                null,
                null,
                null);
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        String name = cursor.getString(cursor.getColumnIndex(GuestInfoDatabaseContract.GUEST_NAME_VALUE_KEY));
        int partySize = cursor.getInt(cursor.getColumnIndex(GuestInfoDatabaseContract.PARTY_SIZE_VALUE_KEY));
        long id =  cursor.getLong(cursor.getColumnIndex(GuestInfoDatabaseContract.GuestInfoTables._ID));
        GuestInfo info = new GuestInfo(name,partySize,id);
        guestInfoDatabase.close();
        return info;
    }

    public boolean updateRow(GuestInfo info) {
        guestInfoDatabase = guestInfoDatabaseHelper.getWritableDatabase();
        String select = GuestInfoDatabaseContract.GuestInfoTables._ID+"=";

        ContentValues vals = new ContentValues();
        vals.put(GuestInfoDatabaseContract.GUEST_NAME_VALUE_KEY,info.getGuestName());
        vals.put(GuestInfoDatabaseContract.PARTY_SIZE_VALUE_KEY,info.getPartySize());

        int x =  guestInfoDatabase.update(GuestInfoDatabaseContract.GUEST_INFO_TABLE_NAME,vals,select,new String[]{info.getDataBaseID()+""});

        guestInfoDatabase.close();
        return x==0;
    }
}
