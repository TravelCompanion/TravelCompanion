package api.cte;

import java.util.ArrayList;
import java.util.HashMap;

import tools.parse.StringParser;

public class TypeConfiguration {
	private static final String PATH = "./data/types.txt";
	private static TypeConfiguration config;
	private static HashMap<String, PlaceType> hashTypes = new HashMap<String, PlaceType>();
	public static int size;
	public static ArrayList<PlaceType> types = new ArrayList<PlaceType>();

	private TypeConfiguration() {
		ArrayList<String> lines = StringParser.readData(PATH);
		String cur;
		for (int i = 0; i < lines.size(); i++) {
			cur = lines.get(i);
			if (!hashTypes.containsKey(cur)) {
				types.add(PlaceType.createType(cur, i));
				hashTypes.put(cur, types.get(i));
			}
		}
		size = types.size();
	}

	public static TypeConfiguration getConfig() {
		if(config == null) config = new TypeConfiguration();
		return config;
	}

	public static PlaceType get(String name) {
		return hashTypes.get(name);
	}

	public static PlaceType get(int id) {
		return types.get(id);
	}

}
