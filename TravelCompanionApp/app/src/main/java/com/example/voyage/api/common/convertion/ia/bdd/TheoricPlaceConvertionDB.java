package com.example.voyage.api.common.convertion.ia.bdd;

import java.util.ArrayList;

import com.example.voyage.api.api.data.TheoricPlace;
import com.example.voyage.api.common.convertion.ia.TheoricPlaceConverter;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.parse.StringParser;

public class TheoricPlaceConvertionDB implements TheoricPlaceConverter<Monument>{

	
	public TheoricPlace convertFrom(Monument monument) {
		ArrayList<String> lines = StringParser.sliceLine(monument.getType(), ',');
		Matrix typesMon = new Matrix(TypeConfiguration.number,1);
		int k = 0;
		for(String line : lines){
			typesMon.setValue(TypeConfiguration.get(line).getId(), 0, 1);
			k++;
		}
		TheoricPlace theoricPlace = new TheoricPlace(monument.getId_monument(),monument.getName_monument(),typesMon,monument.getNote(),monument.getDistance(),k);
		return theoricPlace;
	}

	public Monument convertTo(TheoricPlace data) {
		
		String pref = ""+data.getTypes().getValue(0, 0);
		for(int i = 1; i < data.getTypes().sizeX;i++)
			pref += data.getTypes().getValue(i, 0);
		return new Monument(data.getId(),data.getName(),pref,data.getNote(), data.getDistance());
	}

}
