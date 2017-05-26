package api.cte;

import java.util.ArrayList;
import java.util.HashMap;

import tools.math.Matrix;

public class TypeManager {
	public static Matrix typeListToMatrix(ArrayList<PlaceType> types) {
		Matrix m = new Matrix(TypeConfiguration.size, 1);
		for (PlaceType type : types)
			m.getMatrix()[type.getId()][1] = 1;
		return m;
	}

	public static Matrix typeMapToMatrix(HashMap<PlaceType, Double> types) {
		Matrix m = new Matrix(TypeConfiguration.size, 1);
		for (PlaceType type : types.keySet())
			m.getMatrix()[type.getId()][1] = types.get(type);
		return m;
	}

	public static ArrayList<PlaceType> matrixTotypeList(Matrix m) {
		ArrayList<PlaceType> types = new ArrayList<>();
		for (int i = 0; i < m.sizeX; i++)
			if (m.getMatrix()[i][1] != 0)
				types.add(TypeConfiguration.get(i));
		return types;
	}

	public static HashMap<PlaceType, Double> matrixTotypeMap(Matrix m) {
		HashMap<PlaceType, Double> types = new HashMap<PlaceType, Double>();
		for (int i = 0; i < m.sizeX; i++)
			if (m.getMatrix()[i][1] != 0)
				types.put(TypeConfiguration.get(i), m.getMatrix()[i][1]);
		return types;
	}
}
