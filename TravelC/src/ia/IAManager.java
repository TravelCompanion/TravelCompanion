package ia;

import java.util.Random;

import cte.Constants;
import cte.PlaceType;
import gps.GPSMap;
import gps.Place;
import tools.math.CoordinatesDouble;
import tools.math.RandomDouble;

public class IAManager {
	
	public static CoordinatesDouble choosePlace(TheoricUser user,GPSMap map){
		double val = -1000; 
		CoordinatesDouble c = user.getPosition();
		for(Place place: map.getPlaces().values()){
			if(CoordinatesDouble.eq(user.getPosition(), place.getCoords() )|| user.hasVisited(place.getCoords()))continue;
			double v = calculateDistance(user,place);
			if(val < v);
				{
					c = place.getCoords();
					val = v;	
				}
		}
		return c;		
	}
	
	public static double notePlace(TheoricUser user,Place place){
		double note = 0;
		Random rand = new Random();
		for(PlaceType type : place.getPlaceTypes())
			note += getTypeNote(rand,user, type);
		return note/place.getPlaceTypes().size();
	}
	
	private static double getTypeNote(Random rand,TheoricUser user, PlaceType place) {
		double medium = user.getPreferences().get(place)*Constants.MAX_NOTE;
		return RandomDouble.generateCentered(rand, medium, Constants.MAX_NOTE/20);
	}

	private static double calculateDistance(TheoricUser user,Place place){
		double val = 0;
		for(PlaceType type : place.getPlaceTypes())
			val += 1 - user.getPreferences().get(type);
		return val;
	}
	
	public static void learn(TheoricUser user, Place place, double note,double step){
		for(PlaceType type : place.getPlaceTypes())
			user.getPreferences().replace(type, simpleAdapt(user.getPreferences().get(type), note, step));
	}
	
	private static double simpleAdapt(double oldValue,double note,double step){
			return oldValue + (step*(note - oldValue));
	}
}
