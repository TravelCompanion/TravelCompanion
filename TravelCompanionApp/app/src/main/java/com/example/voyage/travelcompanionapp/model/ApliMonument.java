package com.example.voyage.travelcompanionapp.model;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class ApliMonument implements Comparable<ApliMonument> {

    private int id;
    private String name;
    private LatLng geoloc;
    private String description;
    private Double[] distance;
    private String ville;
    private int note;
    private String type;

    public ApliMonument() {
     }
    public ApliMonument(int id, String name, String type, double note, double distance) {
        this.id = id;
        this.name =name;
        this.type =type;
        this.note =(int)note;
        this.distance = new Double[]{distance};
    }


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
        this.id = id;
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

        return id;
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

    
    public int compareTo(ApliMonument apliMonument) {
        int resultat = 0;
        if (this.getDistance()[0] > apliMonument.getDistance()[0])
            resultat = 1;
        if (this.getDistance()[0] < apliMonument.getDistance()[0])
            resultat = -1;
        if (this.getDistance()[0] == apliMonument.getDistance()[0])
            resultat = 0;
        return resultat;
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
