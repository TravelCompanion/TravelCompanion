package com.example.voyage.api.externalData;

import java.util.ArrayList;

import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.api.tools.parse.StringParseGenerable;
import com.example.voyage.api.tools.parse.StringParser;
import com.example.voyage.api.cte.PlaceType;
import com.example.voyage.api.gps.Place;

public class VirtualPlace implements StringParseGenerable<VirtualPlace, CoordinatesDouble> {
	/**
	 * Its a transition class between business class and IO class for places
	 * data
	 */
	private String name = "place";
	private double x, y;
	private ArrayList<PlaceType> types = new ArrayList<PlaceType>();
	private double note;

	public String getName() {
		return name;
	}

	public double getNote() {
		return note;
	}

	// private double note;
	public VirtualPlace() {
	};

	public VirtualPlace(String name, double x, double y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public static Place toPlace(VirtualPlace td) {
		Place place = new Place(td.name, new double[] { td.x, td.y },td.note);
		for (PlaceType type : td.types)
			place.getPlaceTypes().add(type);
		return place;
	}

	public String getStringKey() {
		return name;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public ArrayList<PlaceType> getTypes() {
		return types;
	}

	public VirtualPlace generateItem(ArrayList<String> args) {
		// data format : name,t1:v/t2:v/t3:v ... ,x,y
		System.out.println(args);
		this.name = args.get(0);
		this.x = Double.parseDouble(args.get(2));
		this.y = Double.parseDouble(args.get(3));
		this.note = Double.parseDouble(args.get(4))/5;
		//ArrayList<String> lines = StringParser.sliceLine(args.get(1), '/');
		ArrayList<String> lines = StringParser.sliceLine(args.get(1), ',');
		for (String line : lines) {
			types.add(PlaceType.getPlaceType(line));
		}
		return this;
	}

	public CoordinatesDouble getKey() {
		CoordinatesDouble coordinatesDouble = new CoordinatesDouble(new double[] { x, y });
		return coordinatesDouble;
	}

	public StringParseGenerable<VirtualPlace, CoordinatesDouble> init() {
		return new VirtualPlace();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((types == null) ? 0 : types.hashCode());
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VirtualPlace other = (VirtualPlace) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	public String toString() {
		return "TempDataDB [name=" + name + ", x=" + x + ", y=" + y + ", types=" + types + "]";
	}

}
