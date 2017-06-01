package api.data;


import java.util.ArrayList;

import common.convertion.PlaceConverter;
import common.type.TypeConfiguration;
import model.Monument;
import tools.math.CoordinatesDouble;
import tools.math.Matrix;
import tools.parse.StringParser;

public class TheoricPlace implements PlaceConverter<TheoricPlace>{
	
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

	public TheoricPlace fromDatabasePlace(Monument monument) {
		ArrayList<String> lines = StringParser.sliceLine(monument.getType(), ',');
		Matrix typesMon = new Matrix(TypeConfiguration.number,1);
		int k = 0;
		for(String line : lines){
			typesMon.setValue(TypeConfiguration.get(line).getId(), 0, 1);
			k++;
		}
		TheoricPlace theoricPlace = new TheoricPlace(monument.getName_monument(),typesMon);
		theoricPlace.numberOfTypes = k;
		return theoricPlace;
	}

	@Override
	public Monument toVirtualPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	public double getNote() {
		return note;
	}

	public int getNumberOfTypes() {
		// TODO Auto-generated method stub
		return numberOfTypes;
	}
	
	

}
