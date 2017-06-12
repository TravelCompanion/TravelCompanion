package com.example.voyage.api.common.convertion.iabdd;

import com.example.voyage.api.common.type.PlaceType;

import java.util.HashMap;

import com.example.voyage.api.common.convertion.generic.UserConverter;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.data.TheoricUser;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.travelcompanionapp.model.ApliUser;

public class TheoricUserConverter implements UserConverter<TheoricUser> {



    public TheoricUser fromDatabase(ApliUser data) {
        Matrix matrix= new Matrix(1,TypeConfiguration.number);
        for(PlaceType plc :data.getPreferences().keySet()){
            matrix.setValue(1,plc.getId(),data.getPreferences().get(plc));
        }
        return new TheoricUser(data.getId(),data.getPosition(),matrix);
    }


    public ApliUser toDataBase(TheoricUser data) {
        HashMap<PlaceType, Double> preferenceApliUser = new HashMap<PlaceType, Double>();
        for (int i = 0; i < TypeConfiguration.number; i++) {
            preferenceApliUser.put(TypeConfiguration.get(i), data.getPreferences().getValue(0, i));
        }
        return new ApliUser(data.getUserName(), data.getPosition(), preferenceApliUser);
    }
}
