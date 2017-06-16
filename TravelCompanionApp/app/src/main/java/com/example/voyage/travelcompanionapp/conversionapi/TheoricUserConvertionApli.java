package com.example.voyage.travelcompanionapp.conversionapi;

import com.example.voyage.api.api.data.TheoricMainUser;
import com.example.voyage.api.api.data.TheoricUser;
import com.example.voyage.api.common.convertion.ia.TheoricPlaceConverter;
import com.example.voyage.api.common.convertion.ia.TheoricUserConverter;
import com.example.voyage.api.common.type.PlaceType;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.model.Utilisateur;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.parse.StringParser;
import com.example.voyage.travelcompanionapp.model.ApliUser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by stanley on 16/06/2017.
 */

public class TheoricUserConvertionApli implements TheoricUserConverter<ApliUser>{

    public TheoricUser convertFrom(ApliUser data) {
        Matrix pref = new Matrix(1, TypeConfiguration.number);
        for (PlaceType placeType : data.getPreferences().keySet())
            pref.setValue(0, placeType.getId(),data.getPreferences().get(placeType));
        TheoricUser theoricUser = new TheoricUser(Integer.parseInt(data.getId()),data.getUsername(),pref);
        return theoricUser;
    }

    public TheoricMainUser convertFromToMainUser(ApliUser data) {

        Matrix pref = new Matrix(1, TypeConfiguration.number);
        for (PlaceType placeType : data.getPreferences().keySet())
            pref.setValue(0, placeType.getId(),data.getPreferences().get(placeType));
        TheoricMainUser theoricMainUser = new TheoricMainUser(Integer.parseInt(data.getId()),data.getUsername(), pref);
        return theoricMainUser;
    }

    public ApliUser convertTo(TheoricUser data) {
        Matrix tmp = data.getPreferences();
        HashMap<PlaceType,Double> pref = new HashMap<PlaceType, Double>();
        for (int i = 1; i < tmp.sizeY; i++)
            pref.put(TypeConfiguration.get(i),tmp.getValue(0,i));
        return new ApliUser(data.getId(),data.getUserName(),pref);
    }
}
