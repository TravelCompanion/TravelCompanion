package api.externalData;

import java.util.ArrayList;

import api.cte.PlaceType;
import api.cte.TypeConfiguration;
import api.gps.Place;
import tools.math.CoordinatesDouble;
import tools.parse.StringParseGenerable;
import tools.parse.StringParseLoggable;
import tools.parse.StringParser;

public class VirtualPlace implements StringParseGenerable<VirtualPlace, CoordinatesDouble>, StringParseLoggable {
	/**
	 * Its a transition class between business class and IO class for places
	 * data
	 */
	private String name = "place";
	private CoordinatesDouble position;
	private ArrayList<PlaceType> types = new ArrayList<PlaceType>();
	private double note;

	
	
	public VirtualPlace(String name, CoordinatesDouble position, ArrayList<PlaceType> types, double note) {
		this.name = name;
		this.position = position;
		this.types = types;
		this.note = note;
	}

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
		position = new CoordinatesDouble(new double[] { x, y });
		this.name = name;
	}

	public static Place toPlace(VirtualPlace td) {
		Place place = new Place(td.name, td.getPosition(), td.note);
		for (PlaceType type : td.types)
			place.getPlaceTypes().add(type);
		return place;
	}

	public String getStringKey() {
		return name;
	}

	public CoordinatesDouble getPosition() {
		return position;
	}

	public ArrayList<PlaceType> getTypes() {
		return types;
	}

	public VirtualPlace generateItem(ArrayList<String> args) {
		// data format : name,t1:v/t2:v/t3:v ... ,x,y
		this.name = args.get(0);
		this.position = new CoordinatesDouble(
				new double[] { Double.parseDouble(args.get(2)), Double.parseDouble(args.get(3)) });
		this.note = Double.parseDouble(args.get(4)) / 5;
		// ArrayList<String> lines = StringParser.sliceLine(args.get(1), '/');
		ArrayList<String> lines = StringParser.sliceLine(args.get(1), ',');
		for (String line : lines) {
			types.add(TypeConfiguration.get(line));
		}
		return this;
	}

	public CoordinatesDouble getKey() {
		CoordinatesDouble coordinatesDouble = position;
		return coordinatesDouble;
	}

	public StringParseGenerable<VirtualPlace, CoordinatesDouble> init() {
		return new VirtualPlace();
	}

	

	public String toString() {
		return "TempDataDB [name=" + name + ", x=" + position.getX() + ", y=" + position.getY() + ", types=" + types + "]";
	}

	@Override
	public String toLog() {
		// data format : name,t1:v/t2:v/t3:v ... ,x,y
		String typesString = PlaceType.typeToString(types.get(0));
		for(int i = 1; i < types.size();i++)
			typesString+= ","+PlaceType.typeToString(types.get(i));
		return name+"/"+typesString+"/"+position.getX()+"/"+position.getY()+"/"+note+";";
	}

}
