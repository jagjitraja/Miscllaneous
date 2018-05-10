package com.example.t00533766.tourguide;

/**
 * Created by T00533766 on 10/4/2017.
 */


public class City {

    private String name;
    private String description;

    City(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
