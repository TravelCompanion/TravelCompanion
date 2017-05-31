package api.gps;

import java.util.HashMap;

import api.data.TheoricPlace;
import common.data.NoPlaceFoundException;
import common.data.VirtualPlace;
import tools.math.CoordinatesDouble;

public class GPSMap {
	// Simulation GPS
	private static GPSMap gps;
	private static HashMap<CoordinatesDouble, TheoricPlace> map = new HashMap<CoordinatesDouble, TheoricPlace>();
	private GPSMap(){}
	public static GPSMap getMap() {
		if (gps == null)
			gps = new GPSMap();
		return gps;
	}

	public static void fillMap(HashMap<CoordinatesDouble, VirtualPlace> datas) throws NoPlaceFoundException {
		HashMap<CoordinatesDouble, TheoricPlace> places = new HashMap<CoordinatesDouble, TheoricPlace>();
		for (CoordinatesDouble data : datas.keySet())
			places.put(data, new TheoricPlace().fromVirtualPlace(datas.get(data)));
		if(places.isEmpty())throw new NoPlaceFoundException();
		map = places;
	}

	public static void clearMap(){
		map.clear();
	}
	
	public static HashMap<CoordinatesDouble, TheoricPlace> getPlaces(){
		return map;
	}
	
	public static TheoricPlace getPlaceAt(CoordinatesDouble coordinates){
		return map.get(coordinates);
	}
	
	public static int placeInRange(){
		return map.size();
	}
	
	public String toString() {
		String elt = "|";
		for(TheoricPlace place : map.values())
			{
			elt+= place+"|";
			}
		return elt;
	}
	
}
