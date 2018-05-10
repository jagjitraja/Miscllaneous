package com.example.musfiqrahman.waitlist.Database;

import android.provider.BaseColumns;

/**
 * Created by T00533766 on 1/15/2018.
 */

public class GuestInfoDatabaseContract {

    private GuestInfoDatabaseContract(){};

    public final static String GUEST_NAME_VALUE_KEY = "guestNameInContentValues";
    public final static String PARTY_SIZE_VALUE_KEY = "partySizeInContentValues";


    public static class GuestInfoTables implements BaseColumns{
        public final static String COLUMN_GUEST_NAME = "guestName";
        public final static String COLUMN_GUEST_PARTY_SIZE = "guestPartySize";

        public final static String [] GUEST_TABLE_COLUMNS = {GuestInfoTables._ID,GuestInfoTables.COLUMN_GUEST_NAME,GuestInfoTables.COLUMN_GUEST_PARTY_SIZE};
    }

    public final static String GUEST_INFO_TABLE_NAME = "GUEST_INFOS";

    public final static String CREATE_GUEST_INFO_TABLE =
            "CREATE TABLE "+ GUEST_INFO_TABLE_NAME + " ( " +
                    GuestInfoTables._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    GuestInfoTables.COLUMN_GUEST_NAME + " TEXT NOT NULL, "+
                    GuestInfoTables.COLUMN_GUEST_PARTY_SIZE + " INTEGER NOT NULL"+
                    " ); ";
    public final static String DELETE_GUEST_INFO_TABLE =
            "DELETE "+ GUEST_INFO_TABLE_NAME;

}
