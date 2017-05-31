package api.data;

import common.convertion.PlaceConverter;
import common.data.VirtualPlace;
import common.type.TypeConfiguration;
import common.type.TypeManager;
import tools.math.CoordinatesDouble;
import tools.math.Matrix;

public class TheoricPlace implements PlaceConverter<TheoricPlace> {
	/** Represent places */
	private String name = "place";
	private CoordinatesDouble coords = new CoordinatesDouble(2);
	private Matrix types = new Matrix(TypeConfiguration.number, 1);
	private int numberOfTypes;

	private double note = 1;

	public TheoricPlace() {
	}

	public TheoricPlace(String name, CoordinatesDouble coords, Matrix types, double note) {
		this.name = name;
		this.coords = coords;
		this.types = types;
		this.note = note;
	}

	public TheoricPlace(String name, CoordinatesDouble coords, double note) {
		this.name = name;
		this.coords = coords;
		this.note = note;
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

	public TheoricPlace(String name, double[] coords, double note) {
		this.coords = new CoordinatesDouble(coords);
		this.name = name;
		this.note = note;
	}

	public TheoricPlace fromVirtualPlace(VirtualPlace virtualPlace) {
		/** convert a place in virtualPlace */
		TheoricPlace place = new TheoricPlace(virtualPlace.getName(), virtualPlace.getPosition(),
				virtualPlace.getNote());
		place.types = TypeManager.typeListToMatrix(virtualPlace.getTypes());
		place.getTypeNumber();
		return place;
	}

	private void getTypeNumber() {
		int k = 0;
		for (int i = 0; i < this.types.sizeX; i++)
			if (this.types.getMatrix()[i][0] == 1)
				k += 1;
		this.numberOfTypes = k;
	}

	@Override
	public VirtualPlace toVirtualPlace() {
		/** convert a virtualPlace in place */
		// TODO Auto-generated method stub
		return null;
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

	public Matrix getTypes() {
		return types;
	}

	public String toString() {
		return "[" + name + "," + coords + ", " + types + "]";
	}

	public int getNumberOfTypes() {
		return numberOfTypes;
	}

}
