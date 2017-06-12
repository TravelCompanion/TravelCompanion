package com.example.voyage.travelcompanionapp.model;



import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.voyage.api.common.type.ConfType;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.common.type.PlaceType;
import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.api.tools.parse.StringParser;

public class ApliUser {

    public ApliUser(){}

    private String id;
    private CoordinatesDouble position;
    private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();
    private ArrayList<ApliUser> friends = new ArrayList<ApliUser>();

    public ApliUser(String id, CoordinatesDouble position) {
        this.id = id;
        this.position = position;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ApliUser(String id, CoordinatesDouble position, HashMap<PlaceType, Double> preferences) {
        TypeConfiguration.getConfig();
        this.id = id;
        this.position = position;
        this.preferences = preferences;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ApliUser(String id, CoordinatesDouble position, String pref) {
        TypeConfiguration.getConfig();
        this.id = id;
        this.position = position;
        ArrayList<String> lines = StringParser.sliceLine(pref,',');
        for(int i = 0; i < lines.size();i++)
            preferences.put(TypeConfiguration.get(i),Double.parseDouble(lines.get(i)));
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setPreferences(String pref) {
        TypeConfiguration.getConfig(new ConfType());
        ArrayList<String> lines = StringParser.sliceLine(pref,',');
        for(int i = 0; i < lines.size();i++)preferences.put(TypeConfiguration.get(i),Double.parseDouble(lines.get(i)));

    }

    public String getId() {
        return id;
    }

    public CoordinatesDouble getPosition() {
        return position;
    }

    public HashMap<PlaceType, Double> getPreferences() {
        return preferences;
    }

    public ArrayList<ApliUser> getFriends() {
        return friends;
    }
}
