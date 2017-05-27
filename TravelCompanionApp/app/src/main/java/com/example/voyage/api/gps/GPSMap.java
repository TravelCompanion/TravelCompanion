package com.example.voyage.api.gps;

import java.util.HashMap;

import com.example.voyage.api.externalData.VirtualPlace;
import com.example.voyage.api.tools.math.CoordinatesDouble;

public class GPSMap {
	// Simulaiton GPS
	private static GPSMap gps;
	private static HashMap<CoordinatesDouble, Place> map = new HashMap<CoordinatesDouble, Place>();
	
	public static GPSMap getMap() {
		if (gps == null)
			gps = new GPSMap();
		return gps;
	}

	public static void fillMap(HashMap<CoordinatesDouble, VirtualPlace> datas) throws NoPlaceFoundException {
		HashMap<CoordinatesDouble, Place> places = new HashMap<CoordinatesDouble, Place>();
		for (CoordinatesDouble data : datas.keySet())
			places.put(data, VirtualPlace.toPlace(datas.get(data)));
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
