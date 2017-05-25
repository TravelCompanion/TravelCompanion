package com.example.voyage.travelcompanionapp;



import java.util.HashMap;

import com.example.voyage.api.cte.PlaceType;
import com.example.voyage.api.externalData.VirtualUser;
import com.example.voyage.api.tools.math.CoordinatesDouble;

public class User {

    public User(){}

    public User(String id, CoordinatesDouble position) {
        this.id = id;
        this.position = position;

    }

    private String id;
    private CoordinatesDouble position;
    private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();

    public String getId() {
        return id;
    }

    public CoordinatesDouble getPosition() {
        return position;
    }

    public HashMap<PlaceType, Double> getPreferences() {
        return preferences;
    }

    public static User toRealUser(VirtualUser virtualUser) {
        User user = new User(virtualUser.getId(), virtualUser.getPosition());
        for (PlaceType type : virtualUser.getPreferences().keySet())
            user.getPreferences().put(type, virtualUser.getPreferences().get(type));
    return  user;
    }

    public static VirtualUser fromUser(User user) {
        VirtualUser virtualUser = new VirtualUser(user.getId(), user.getPosition());
        for (PlaceType type : user.getPreferences().keySet())
            virtualUser.getPreferences().put(type, user.getPreferences().get(type));
        return virtualUser;
    }

}
