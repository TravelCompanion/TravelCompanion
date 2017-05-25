package api.gps;

import java.util.ArrayList;

import api.cte.PlaceType;
import tools.math.CoordinatesDouble;

public class Place {
	/**Represent places*/
	private String name = "place";
	private CoordinatesDouble coords = new CoordinatesDouble(2);
	private ArrayList<PlaceType> placeTypes = new ArrayList<PlaceType>();
	private double note = 1;

	public Place() {
	}
	
	public Place(String name, CoordinatesDouble coords, ArrayList<PlaceType> placeTypes, double note) {
		this.name = name;
		this.coords = coords;
		this.placeTypes = placeTypes;
		this.note = note;
	}

	public Place(String name, CoordinatesDouble coords, double note) {
		this.name = name;
		this.coords = coords;
		this.note = note;
	}

	public Place(CoordinatesDouble coordinates) {
		this.coords = coordinates;
	}

	public Place(double[] coords) {
		this.coords = new CoordinatesDouble(coords);
	}

	public Place(String name, CoordinatesDouble coordinates) {
		this.coords = coordinates;
		this.name = name;
	}

	public Place(String name, double[] coords) {
		this.coords = new CoordinatesDouble(coords);
		this.name = name;
	}

	public Place(String name, double[] coords, double note) {
		this.coords = new CoordinatesDouble(coords);
		this.name = name;
		this.note = note;
	}

	public double getNote() {
		return note;
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
		return "[" + name + "," + coords + ", " + placeTypes + "]";
	}

}
