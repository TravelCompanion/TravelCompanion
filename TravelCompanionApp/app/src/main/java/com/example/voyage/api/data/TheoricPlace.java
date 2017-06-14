package com.example.voyage.api.data;



import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.api.tools.math.Matrix;

public class TheoricPlace{
	
	private String name = "place";
	private CoordinatesDouble coords = new CoordinatesDouble(2);
	private double note;
	private Matrix types;
	private int numberOfTypes;
	
	public TheoricPlace(){}
	
	public TheoricPlace(CoordinatesDouble coordinates){
		this.coords = coordinates;
		}
	public TheoricPlace(double[] coords){
		this.coords = new CoordinatesDouble(coords);
		}
	
	public TheoricPlace(String name, CoordinatesDouble coordinates){
		this.coords = coordinates;
		this.name = name;
	}
	public TheoricPlace(String name , double[] coords){
		this.coords = new CoordinatesDouble(coords);
		this.name = name;
	}

	
	
	public TheoricPlace(String name, Matrix types) {
		this.name = name;
		this.types = types;
	}
	
	public TheoricPlace(String name, Matrix types,int typeNum) {
		this.name = name;
		this.types = types;
		this.numberOfTypes = typeNum;
	}

	public String getName() {
		return name;
	}

	public CoordinatesDouble getCoords() {
		return coords;
	}

	
	public String toString() {
		return "Place [" + name + ", coords :" + coords + ", "
				+ types + "]";
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
	
	

}