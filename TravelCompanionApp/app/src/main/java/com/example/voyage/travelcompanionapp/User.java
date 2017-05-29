package com.example.voyage.travelcompanionapp;



import java.util.ArrayList;
import java.util.HashMap;

import com.example.voyage.api.cte.PlaceType;
import com.example.voyage.api.externalData.VirtualUser;
import com.example.voyage.api.tools.math.CoordinatesDouble;

public class User {

    public User(){}

    private String id;
    private CoordinatesDouble position;
    private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();
    private ArrayList<User> friends = new ArrayList<User>();

    public User(String id, CoordinatesDouble position) {
        this.id = id;
        this.position = position;

    }

    public User(String id, CoordinatesDouble position,ArrayList<VirtualUser> virtualUsers) {
        this.id = id;
        this.position = position;
        for(VirtualUser virtualUser : virtualUsers)
            friends.add(toRealUser(virtualUser,false));
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

    public static User toRealUser(VirtualUser virtualUser,boolean isMainUser) {
        //new User(virtualUser.getId(), virtualUser.getPosition().getFriendList()) :new User(virtualUser.getId(), virtualUser.getPosition());
        User user =new User(virtualUser.getId(), virtualUser.getPosition());
        // initialiser au début de l'execution TypeConfiguration.getConfig();
        // a corriger utiliser TypeConfiguration
        for (PlaceType type : virtualUser.getPreferences().keySet())
            user.getPreferences().put(type, virtualUser.getPreferences().get(type));
    return  user;
    }

    public static VirtualUser fromUser(User user) {
        VirtualUser virtualUser = new VirtualUser(user.getId(), user.getPosition());
        // a corriger utiliser TypeConfiguration
        for (PlaceType type : user.getPreferences().keySet())
            virtualUser.getPreferences().put(type, user.getPreferences().get(type));
        return virtualUser;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
}
