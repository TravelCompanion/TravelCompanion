package gps;

import java.util.ArrayList;

import cte.PlaceType;
import tools.math.CoordinatesDouble;

public class Place {
	
	private String name = "place";
	private CoordinatesDouble coords = new CoordinatesDouble(2);
	private ArrayList<PlaceType> placeTypes = new ArrayList<PlaceType>();
	private double note;
	public Place(){}
	
	public Place(CoordinatesDouble coordinates){
		this.coords = coordinates;
		}
	public Place(double[] coords){
		this.coords = new CoordinatesDouble(coords);
		}
	
	public Place(String name, CoordinatesDouble coordinates){
		this.coords = coordinates;
		this.name = name;
	}
	public Place(String name , double[] coords){
		this.coords = new CoordinatesDouble(coords);
		this.name = name;
	}

	
	
	public String getName() {
		return name;
	}

	public CoordinatesDouble getCoords() {
		return coords;
	}

	public ArrayList<PlaceType> getPlaceTypes() {
		return placeTypes;
	}

	public String toString() {
		return "Place [" + name + ", coords :" + coords + ", "
				+ placeTypes + "]";
	}
	
	

}
