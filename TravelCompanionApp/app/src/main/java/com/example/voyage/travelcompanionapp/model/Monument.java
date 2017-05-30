package com.example.voyage.travelcompanionapp.model;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class Monument {

    private int Id;
    private String name;
    private LatLng geoloc;
    private String description;
    private float[] distance;

    public float[] getDistance() {
        return distance;
    }

    public void setDistance(float[] distance) {
        this.distance = distance;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGeoloc(LatLng geoloc) {
        this.geoloc = geoloc;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {

        return Id;
    }

    public String getName() {
        return name;
    }

    public LatLng getGeoloc() {
        return geoloc;
    }

    public String getDescription() {
        return description;
    }


public float[] calculdistance (LatLng currentLocation,double latitude, double longitude){
    float[] dist= new float[2];
    Location.distanceBetween(currentLocation.latitude,currentLocation.longitude,latitude,longitude, dist);
    return dist;
}
}
