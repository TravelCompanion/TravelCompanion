package com.example.voyage.travelcompanionapp.model;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class ApliMonument {

    private int Id;
    private String name;
    private LatLng geoloc;
    private String description;
    private Double[] distance;
    private String ville;
    private int note;


    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Double[] getDistance() {
        return distance;
    }

        public void setDistance(Double[] distance) {
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }


public Double[] calculdistance (LatLng currentLocation,double latitude, double longitude){
    float[] dist= new float[2];
    Double[] parsedist=new Double[2];
    String parseSdist;
    Location.distanceBetween(currentLocation.latitude,currentLocation.longitude,latitude,longitude, dist);
    parseSdist=String.valueOf(dist[0]);
    parsedist[0]=Double.valueOf(new Double(parseSdist));
    parseSdist=String.valueOf(dist[1]);
    parsedist[1]=Double.valueOf(new Double(parseSdist));

    return parsedist;
}
}
