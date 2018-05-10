package com.example.musfiqrahman.waitlist;

/**
 * Created by musfiqrahman on 2018-01-09.
 */

public class GuestInfo {

    private long dataBaseID;
    private String guestName;
    private int partySize;

    public GuestInfo(String guestName, int partySize, long dataBaseID) {
        this.guestName = guestName;
        this.dataBaseID = dataBaseID;
        this.partySize = partySize;
    }

    public GuestInfo(String guestName, int partySize) {
        this.guestName = guestName;
        this.dataBaseID = 0;
        this.partySize = partySize;
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

    public long getDataBaseID() {
        return dataBaseID;
    }

    public void setDataBaseID(long dataBaseID) {
        this.dataBaseID = dataBaseID;
    }
}
