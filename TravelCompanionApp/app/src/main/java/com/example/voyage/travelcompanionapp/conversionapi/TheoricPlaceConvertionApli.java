package com.example.voyage.travelcompanionapp.conversionapi;

import com.example.voyage.api.api.data.TheoricPlace;
import com.example.voyage.api.common.convertion.ia.TheoricPlaceConverter;
import com.example.voyage.api.common.type.PlaceType;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.parse.StringParser;
import com.example.voyage.travelcompanionapp.model.ApliMonument;

import java.util.ArrayList;

/**
 * Created by stanley on 16/06/2017.
 */

public class TheoricPlaceConvertionApli implements TheoricPlaceConverter<ApliMonument>{

    public TheoricPlace convertFrom(ApliMonument monument) {
        ArrayList<String> lines = StringParser.sliceLine(monument.getType(), ',');
        Matrix typesMon = new Matrix(TypeConfiguration.number,1);
        for(String line : lines) {
            PlaceType placeType = TypeConfiguration.get(line);
            int id = placeType.getId();
            typesMon.setValue(id, 0, 1.0);

        }
        TheoricPlace theoricPlace = new TheoricPlace(monument.getId(),monument.getName(),typesMon,(int)monument.getNote(),monument.getDistance()[0],lines.size());
        return theoricPlace;
    }

    public ApliMonument convertTo(TheoricPlace data) {

        String pref = ""+data.getTypes().getValue(0, 0);
        for(int i = 1; i < data.getTypes().sizeX;i++)
            pref += ","+data.getTypes().getValue(i, 0);
        return new ApliMonument(data.getId(),data.getName(),pref,data.getNote(), data.getDistance());
    }
}
