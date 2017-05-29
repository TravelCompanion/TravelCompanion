package api.gps;

import java.util.HashMap;

import api.data.Place;
import common.data.NoPlaceFoundException;
import common.data.VirtualPlace;
import tools.math.CoordinatesDouble;

public class GPSMap {
	// Simulation GPS
	private static GPSMap gps;
	private static HashMap<CoordinatesDouble, Place> map = new HashMap<CoordinatesDouble, Place>();
	private GPSMap(){}
	public static GPSMap getMap() {
		if (gps == null)
			gps = new GPSMap();
		return gps;
	}

	public static void fillMap(HashMap<CoordinatesDouble, VirtualPlace> datas) throws NoPlaceFoundException {
		HashMap<CoordinatesDouble, Place> places = new HashMap<CoordinatesDouble, Place>();
		for (CoordinatesDouble data : datas.keySet())
			places.put(data, new Place().fromVirtualPlace(datas.get(data)));
		if(places.isEmpty())throw new NoPlaceFoundException();
		map = places;
	}

	public static void clearMap(){
		map.clear();
	}
	
	public static HashMap<CoordinatesDouble, Place> getPlaces(){
		return map;
	}
	
	public static Place getPlaceAt(CoordinatesDouble coordinates){
		return map.get(coordinates);
	}
	
	public static int placeInRange(){
		return map.size();
	}
	
	public String toString() {
		String elt = "|";
		for(Place place : map.values())
			{
			elt+= place+"|";
			}
		return elt;
	}
	
}
