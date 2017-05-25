package api.externalData.generators;

import java.util.HashMap;
import java.util.Random;

import api.cte.PlaceType;
import tools.math.CoordinatesDouble;
import tools.math.random.RandomDouble;

public class DataGenerator {
	protected static HashMap<PlaceType, Double> generatePreferences() {
		Random rand = new Random();
		HashMap<PlaceType, Double> preferences = new HashMap<PlaceType,Double>();
		for(PlaceType type : PlaceType.values())
			preferences.put(type, RandomDouble.generate(rand,1, 0));
		return preferences;
	}
	
	protected static CoordinatesDouble generatePosition() {
		Random rand = new Random();
		return  new CoordinatesDouble(new double[]{RandomDouble.generate(rand,50,48),RandomDouble.generate(rand,50,48)});
	}
	
	protected static String generateName(String base,int number) {
		return base+number;
	}
}
