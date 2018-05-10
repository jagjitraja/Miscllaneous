package com.example.t00533766.room.RoomFiles;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.example.t00533766.room.GuestInfoProvider.GuestInfoContract;

/**
 * Created by musfiqrahman on 2018-01-09.
 */
@Entity
public class GuestInfo {

    @PrimaryKey(autoGenerate = true)
    private long guest_id;

    @ColumnInfo(name = GuestInfoContract.COLUMN_GUEST_NAME)
    private String guestName;

    @ColumnInfo(name =  GuestInfoContract.COLUMN_PARTY_SIZE)
    private int partySize;

    public GuestInfo(String guestName, int partySize) {
        this.guestName = guestName;
        this.partySize = partySize;
    }

    public GuestInfo() {
        this.guestName = "";
        this.guest_id = 0;
        this.partySize = 0;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    @Override
    public String toString() {
        return guestName+" "+partySize;
    }

    public long getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(long guest_id) {
        this.guest_id = guest_id;
    }
}
