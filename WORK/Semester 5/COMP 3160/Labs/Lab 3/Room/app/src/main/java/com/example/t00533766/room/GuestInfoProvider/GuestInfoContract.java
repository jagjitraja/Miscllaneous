package com.example.t00533766.room.GuestInfoProvider;

import com.example.t00533766.room.RoomFiles.GuestInfo;
import com.example.t00533766.room.RoomFiles.GuestInfoDatabase;

/**
 * Created by T00533766 on 1/30/2018.
 */

public class GuestInfoContract {

    public static final String APP_AUTHORITY = "com.example.t00533766.room.GuestInfoProvider";
    public static final String SCHEME = "content://";

    public static final String DATABASE_NAME = GuestInfoDatabase.class.getSimpleName();
    public static final String TABLE_NAME = "guest_info_table";

    public static final String COLUMN_GUEST_ID = "guest_id";
    public static final String COLUMN_GUEST_NAME = "guest_name";
    public static final String COLUMN_PARTY_SIZE = "party_size";

    public static final String BASE_URI = SCHEME+APP_AUTHORITY+"/";
    public static final String GUEST_INFO_TABLE_URI = SCHEME+APP_AUTHORITY+"/"+TABLE_NAME;
    public static final String GUEST_INFO_URI = SCHEME+APP_AUTHORITY+"/"+TABLE_NAME+"/*";

    public static final int CODE_GET_ALL_GUESTS = 1;
    public static final int CODE_GET_SPECIFIC_GUESTS = 2;

    private GuestInfoContract(){}


}
