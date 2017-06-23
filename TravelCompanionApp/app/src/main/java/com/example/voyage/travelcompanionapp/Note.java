package com.example.voyage.travelcompanionapp;


import java.util.ArrayList;

public class Note {
    private float note;
    private String monument;
    private String user;


    private ArrayList<Observer> listObs;

    public void notif() {
        for (Observer obs : listObs) {
            obs.update();
        }

    }
    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getMonument() {
        return monument;
    }

    public void setMonument(String monument) {
        this.monument = monument;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ArrayList<Observer> getListObs() {
        return listObs;
    }

    public void setListObs(ArrayList<Observer> listObs) {
        this.listObs = listObs;
    }


}


