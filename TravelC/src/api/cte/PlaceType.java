package api.cte;

public class PlaceType {
	private String name;
	private int id;

	private PlaceType() {
	}

	private PlaceType(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public static String typeToString(PlaceType type) {
		return type.name;
	}

	public static PlaceType stringToType(String name) {
		return TypeConfiguration.get(name);
	}

	public static int typeToInt(PlaceType type) {
		return type.id;
	}

	public static PlaceType intToType(int i) {
		return TypeConfiguration.get(i);
	}

	public static PlaceType createType(String name, int id) {
		return new PlaceType(name, id);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "PlaceType [name=" + name + ", id=" + id + "]";
	}

}
