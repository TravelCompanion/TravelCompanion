package others.generators;

import java.util.HashMap;
import java.util.Random;

import common.type.PlaceType;
import common.type.TypeConfiguration;
import tools.math.CoordinatesDouble;
import tools.math.random.RandomDouble;

public class DataGenerator {
	public static final double MINX = 48;
	public static final double MAXX = 50;
	public static final double MINY = 48;
	public static final double MAXY = 50;
	protected static HashMap<PlaceType, Double> generatePreferences() {
		Random rand = new Random();
		HashMap<PlaceType, Double> preferences = new HashMap<PlaceType,Double>();
		for(PlaceType type : TypeConfiguration.types)
			preferences.put(type, RandomDouble.generate(rand,1, 0));
		return preferences;
	}
	
	public static CoordinatesDouble generatePosition() {
		Random rand = new Random();
		return  new CoordinatesDouble(new double[]{RandomDouble.generate(rand,MINX,MAXX),RandomDouble.generate(rand,MINY,MAXY)});
	}
	
	protected static String generateName(String base,int number) {
		return base+number;
	}
}
