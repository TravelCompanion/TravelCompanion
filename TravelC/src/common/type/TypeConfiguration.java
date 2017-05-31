package common.type;

import java.util.ArrayList;
import java.util.HashMap;

import tools.parse.StringParser;

public class TypeConfiguration {
	/**this class manage the types*/
	private static final String PATH = "./data/types.txt";
	/**path to file witch define the type*/
	private static TypeConfiguration config;
	private static HashMap<String, PlaceType> hashTypes = new HashMap<String, PlaceType>();
	/**allow to access to types by using names*/
	public static int number;
	/**number of types*/
	public static ArrayList<PlaceType> types = new ArrayList<PlaceType>();
	/**list of types*/

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
		number = types.size();
	}

	public static TypeConfiguration getConfig() {
		/**get the this object or initialize if it's null*/
		if(config == null) config = new TypeConfiguration();
		return config;
	}

	public static PlaceType get(String name) {
		/**get the type with corresponding name*/
		return hashTypes.get(name);
	}

	public static PlaceType get(int id) {
		/**get the type with corresponding id*/
		return types.get(id);
	}

}
