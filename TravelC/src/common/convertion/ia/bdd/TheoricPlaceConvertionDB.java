package common.convertion.ia.bdd;

import java.util.ArrayList;

import api.data.TheoricPlace;
import common.convertion.ia.TheoricPlaceConverter;
import common.type.TypeConfiguration;
import model.Monument;
import tools.math.Matrix;
import tools.parse.StringParser;

public class TheoricPlaceConvertionDB implements TheoricPlaceConverter<Monument>{

	
	public TheoricPlace convertFrom(Monument monument) {
		ArrayList<String> lines = StringParser.sliceLine(monument.getType(), ',');
		Matrix typesMon = new Matrix(TypeConfiguration.number,1);
		int k = 0;
		for(String line : lines){
			typesMon.setValue(TypeConfiguration.get(line).getId(), 0, 1);
			k++;
		}
		TheoricPlace theoricPlace = new TheoricPlace(monument.getName_monument(),typesMon,monument.getNote(),k);
		return theoricPlace;
	}

	public Monument convertTo(TheoricPlace data) {
		// TODO Auto-generated method stub
		return null;
	}

}
