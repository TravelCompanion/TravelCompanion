package ia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import cte.Constants;
import cte.PlaceType;
import gps.GPSMap;
import gps.Place;
import tools.math.CoordinatesDouble;
import tools.math.compare.CompareUnitDouble;
import tools.math.compare.MathUnitComparator;
import tools.math.random.RandomDouble;

public class IAManager {
	
	public static CoordinatesDouble choosePlace(TheoricUser user){
		double val = -ConstantIA.INFINITE; 
		CoordinatesDouble c = user.getPosition();
		for(Place place: GPSMap.getPlaces().values()){
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
	
	public static ArrayList<CoordinatesDouble> chooseListOfPlace(TheoricUser user){
		ArrayList<CompareUnitDouble<CoordinatesDouble>> units = new ArrayList<CompareUnitDouble<CoordinatesDouble>>();
		ArrayList<CoordinatesDouble> coords = new ArrayList<CoordinatesDouble>();

		for(Place place: GPSMap.getPlaces().values()){
			if(CoordinatesDouble.eq(user.getPosition(), place.getCoords() )|| user.hasVisited(place.getCoords()))continue;
			double v = calculateDistance(user,place);
			units.add(new CompareUnitDouble<CoordinatesDouble>(v, place.getCoords()));
		} 
		units.sort(MathUnitComparator.getByNameDouble("<"));
		System.out.println(units);
		for(CompareUnitDouble<CoordinatesDouble> cud : units)
			coords.add(cud.getElement());
		return coords;
	}
	
	private static HashMap<PlaceType, Double> getErrorByPlaceType(TheoricUser user, Place place, double note){
		HashMap<PlaceType, Double> errors = new HashMap<PlaceType,Double>();
		for(PlaceType type : place.getPlaceTypes())
			errors.put(type, note - user.getPreferences().get(type)*place.getNote());
		return errors;		
	}
	
	private static double getGlobalError(Place place, HashMap<PlaceType, Double> errors){
		double errG = 0;
		for(Double err : errors.values())
			errG += err;
		System.out.println("types : "+place.getPlaceTypes().size() +"errors : "+errors.values().size() +"errG : " +errG/place.getPlaceTypes().size());
		return errG/place.getPlaceTypes().size();
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
			val += Math.pow((1 - user.getPreferences().get(type)),2);
		return val/place.getPlaceTypes().size();
	}
	
	public static void perceptronLearn(TheoricUser user, Place place, double note,double eps){
		HashMap<PlaceType, Double> errors = getErrorByPlaceType(user, place, note);
		double errG = getGlobalError(place, errors);
		Double pref;
		for(PlaceType type : place.getPlaceTypes())
			{
			pref = user.getPreferences().get(type);
			user.getPreferences().replace(type,pref+(eps*errG));
			}
	}

	
	public static void majorityJudgment(){
		
	}
	
	public static void learn(TheoricUser user, Place place, double note,double step){
		for(PlaceType type : place.getPlaceTypes())
			user.getPreferences().replace(type, simpleAdapt(user.getPreferences().get(type), note, step));
	}
	
	private static double simpleAdapt(double oldValue,double note,double step){
			return oldValue + (step*(note - oldValue));
	}

}


