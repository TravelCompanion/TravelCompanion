package sim;


import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.ArrayList;

import cte.Constants;
import cte.PlaceType;
import externalData.VirtualDataBase;
import gps.GPSMap;
import gps.NoPlaceFoundException;
import gps.Place;
import ia.IAManager;
import ia.TheoricUser;
import tools.ia.Perceptron;
import tools.ia.decition.AbstractDecition;
import tools.ia.decition.DefaultDecition;
import tools.math.CoordinatesDouble;
import tools.math.compare.CompareUnitDouble;
import tools.math.compare.MathUnitComparator;

public class Simulation2 {
	public static void main(String[] args) {

		/*
		 * HashMap<String, VirtualUser> users=
		 * VirtualDataBase.getDataBase().getUsers(); ArrayList<String> lines =
		 * new ArrayList<String>(); for(VirtualUser user : users.values())
		 * lines.add(user.toLog()); System.out.println(users);
		 * System.out.println(users.size()+" "+lines.size());
		 * StringParser.writeData(Constants.DB_PATH_USERDEST, lines);
		 */
		/**
		 * Simulate for numberIteration iterations the behavior of user on a virtual map and
		 * screen the evolution of its profile
		 */
		TheoricUser user = new TheoricUser();
		GPSMap.getMap();
		VirtualDataBase virtualDataBase = VirtualDataBase.getDataBase();

		CoordinatesDouble next;
		
		double[] weights = new double[user.getPreferences().size()];
		double[] entry = new double[user.getPreferences().size()];
		for(int i = 0; i < weights.length; i++)
			weights[i] = 0.5;
		Perceptron neuro = new Perceptron(weights, 0.2, new DefaultDecition());
		double note;
		int numberIteration = 1;
		for (int i = 0; i < numberIteration; i++) {
			System.out.println("\niteration : " + (i + 1));
			try {
				GPSMap.fillMap(virtualDataBase.requestNearPlace(user, Constants.SCAN_SIZE));
			} catch (NoPlaceFoundException e) {
				e.printStackTrace();
			}
			
			
			ArrayList<CompareUnitDouble<Place>> places = new ArrayList<CompareUnitDouble<Place>>();
			for(Place place : GPSMap.getPlaces().values()){
				int k = 0;
				for(PlaceType type : user.getPreferences().keySet()){
					entry[k] =  place.getPlaceTypes().contains(type) ? 1 :0; 
				}
				places.add(new CompareUnitDouble<Place>(neuro.propagate(entry, 0), place));
			}
			places.sort(MathUnitComparator.getByNameDouble("<"));
			ArrayList<Place> result = new ArrayList<Place>();
			for(int j = 0; j < places.size();j++)
				result.add(places.get(j).getElement());
			System.out.println(result);
			next = result.get(0).getCoords();
			
			System.out.println(GPSMap.placeInRange() + " : " + GPSMap.getMap().toString());
			
		}
	}

}
