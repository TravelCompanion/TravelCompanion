package externalData;

import java.util.ArrayList;

import tools.math.CoordinatesDouble;
import tools.parse.StringParseGenerable;
import tools.parse.StringParser;
import cte.PlaceType;
import gps.Place;

public class TempDataDB implements StringParseGenerable<TempDataDB,CoordinatesDouble> {
	private String name = "place";
	private double x, y;
	private ArrayList<PlaceType> types = new ArrayList<PlaceType>();
	public TempDataDB(){};
	public TempDataDB(String name, double x, double y) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public static Place toPlace(TempDataDB td) {
		Place place = new Place(td.name, new double[] { td.x, td.y });
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

	public TempDataDB generateItem(ArrayList<String> args) {
		// data format : name,t1:v/t2:v/t3:v ... ,x,y
		this.name = args.get(0);
		this.x = Double.parseDouble(args.get(2));
		this.y = Double.parseDouble(args.get(3));
		ArrayList<String> lines = StringParser.sliceLine(args.get(1), '/');
		for (String line : lines) {
			types.add(PlaceType.getPlaceType(line));
		}
		return this;
	}
	public CoordinatesDouble getKey() {
		CoordinatesDouble coordinatesDouble = new CoordinatesDouble(new double[]{x,y});
		return coordinatesDouble;
	}
	
	public StringParseGenerable<TempDataDB, CoordinatesDouble> init() {
		return new TempDataDB();
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
		TempDataDB other = (TempDataDB) obj;
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
