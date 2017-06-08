package api.data;



import tools.math.CoordinatesDouble;
import tools.math.Matrix;
import tools.polls.Elegible;

public class TheoricPlace implements Elegible{
	
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
	
	public TheoricPlace(String name, Matrix types,double note,int typeNum) {
		this.name = name;
		this.types = types;
		this.numberOfTypes = typeNum;
		this.note = note;
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
