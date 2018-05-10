package com.example.t00533766.room.GuestInfoProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.t00533766.room.RoomFiles.GuestInfoDatabase;

/**
 * Created by T00533766 on 1/30/2018.
 */

public class GuestInfoProvider extends ContentProvider{

    private GuestInfoDatabase guestInfoDatabase;

    private static UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(GuestInfoContract.APP_AUTHORITY,GuestInfoContract.BASE_URI,GuestInfoContract.CODE_GET_ALL_GUESTS);
        URI_MATCHER.addURI(GuestInfoContract.APP_AUTHORITY,GuestInfoContract.GUEST_INFO_URI,GuestInfoContract.CODE_GET_SPECIFIC_GUESTS);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if(context==null){
            return false;
        }
        guestInfoDatabase = GuestInfoDatabase.getGuestInfoDatabaseInstance(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
            switch (URI_MATCHER.match(uri)){
                case GuestInfoContract.CODE_GET_ALL_GUESTS:
                    return guestInfoDatabase.guestInfoDAO().getAllGuestsInfoCursor();

                case GuestInfoContract.CODE_GET_SPECIFIC_GUESTS:
                    return guestInfoDatabase.guestInfoDAO().getGuestInfo(Long.parseLong(uri.getLastPathSegment()));
                default:
                    return null;
            }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
