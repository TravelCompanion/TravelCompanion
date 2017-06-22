package com.example.voyage.api.api.data;

import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.polls.Elegible;

public class TheoricPlace implements Elegible {

	private int id;
	private String name = "place";
	private CoordinatesDouble coords = new CoordinatesDouble(2);
	private double note;
	private Matrix types;
	private int numberOfTypes;
	private double distance;

	public TheoricPlace() {
	}

	public TheoricPlace(CoordinatesDouble coordinates) {
		this.coords = coordinates;
	}

	public TheoricPlace(double[] coords) {
		this.coords = new CoordinatesDouble(coords);
	}

	public TheoricPlace(String name, CoordinatesDouble coordinates) {
		this.coords = coordinates;
		this.name = name;
	}

	public TheoricPlace(String name, double[] coords) {
		this.coords = new CoordinatesDouble(coords);
		this.name = name;
	}

	public TheoricPlace(String name, Matrix types) {
		this.name = name;
		this.types = types;
	}

	public TheoricPlace(int id, String name, Matrix types, double note, double distance,int typeNum) {
		this.name = name;
		this.types = types;
		this.numberOfTypes = typeNum;
		this.note = note;
		this.id = id;
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public CoordinatesDouble getCoords() {
		return coords;
	}

	public String toString() {
		return "Place [" + name + ", coords :" + coords + ", " + types + "]";
	}

	public Matrix getTypes() {
		return types;
	}

	public double getNote() {
		return note;
	}

	public int getNumberOfTypes() {
		// TODO Auto-generated method stub
		return numberOfTypes;
	}

	public int getId() {

		return id;
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coords == null) ? 0 : coords.hashCode());
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		temp = Double.doubleToLongBits(note);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + numberOfTypes;
		result = prime * result + ((types == null) ? 0 : types.hashCode());
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
		TheoricPlace other = (TheoricPlace) obj;
		if (coords == null) {
			if (other.coords != null)
				return false;
		} else if (!coords.equals(other.coords))
			return false;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(note) != Double.doubleToLongBits(other.note))
			return false;
		if (numberOfTypes != other.numberOfTypes)
			return false;
		if (types == null) {
			if (other.types != null)
				return false;
		} else if (!types.equals(other.types))
			return false;
		return true;
	}

	
}
