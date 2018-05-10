package com.example.t00533766.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by T00533766 on 1/17/2018.
 */

@Dao
public interface GuestInfoDAO {

    @Insert
    public abstract void insertGuestInfo(GuestInfo guestInfo);

    @Query("SELECT * FROM GuestInfo")
    public abstract List<GuestInfo> getAllGuestsInfo();

    @Delete
    public abstract void deleteGuestInfo(GuestInfo guestInfo);

    @Update
    public abstract void updateGuestInfo(GuestInfo guestInfo);

}
