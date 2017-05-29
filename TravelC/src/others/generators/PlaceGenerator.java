package others.generators;

import java.util.ArrayList;
import java.util.HashMap;

import common.data.VirtualPlace;
import common.type.PlaceType;
import tools.math.CoordinatesDouble;
import tools.math.random.RandomDouble;

public class PlaceGenerator extends DataGenerator{
	public static ArrayList<VirtualPlace> generatePlaceList(int numberOfPlaces){
		ArrayList<VirtualPlace> places = new ArrayList<VirtualPlace>();
		for(int i = 0; i < numberOfPlaces; i ++)
			places.add(generatePlace(i+1));
		return places;
	}

	private static VirtualPlace generatePlace(int number) {
		String name = generateName("place", number);
		CoordinatesDouble position = generatePosition();
		ArrayList<PlaceType> types = new ArrayList<PlaceType>();
		HashMap<PlaceType, Double> tmp;
		
		while(types.isEmpty()){
		tmp = generatePreferences();
		for(PlaceType type : tmp.keySet())
			if(tmp.get(type) > 0.5)
				types.add(type);
		}
		
		double note = RandomDouble.generate(1, 0);		
		return new VirtualPlace(name,position,types,note);
	} 
}
