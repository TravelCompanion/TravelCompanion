package com.example.voyage.api.common.type;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.voyage.api.tools.math.Matrix;

public class TypeManager {
	/** this type manage different conversion using the types. */
	public static Matrix typeListToMatrix(ArrayList<PlaceType> types) {
		/**
		 * convert a ArrayList of types in a row Matrix with 0 or 1 value and a size
		 * equal to the number of types.
		 */
		Matrix m = new Matrix(TypeConfiguration.number, 1);
		for (PlaceType type : types)
			m.getMatrix()[type.getId()][0] = 1;
		return m;
	}

	public static Matrix typeMapToRowMatrix(HashMap<PlaceType, Double> types) {
		/**
		 * convert a HashMap of types in a row Matrix with a value equals to the
		 * corresponding type or 0 if don't exist value and a size equal to the
		 * number of types.
		 */
		Matrix m = new Matrix(TypeConfiguration.number, 1);
		for (PlaceType type : types.keySet())
			m.getMatrix()[type.getId()][0] = types.get(type);
		return m;
	}
	
	public static Matrix typeMapToColumnMatrix(HashMap<PlaceType, Double> types) {
		/**
		 * convert a HashMap of types in a row Matrix with a value equals to the
		 * corresponding type or 0 if don't exist value and a size equal to the
		 * number of types.
		 */
		Matrix m = new Matrix(1,TypeConfiguration.number);
		for (PlaceType type : types.keySet())
			m.getMatrix()[0][type.getId()] = types.get(type);
		return m;
	}

	public static ArrayList<PlaceType> matrixTotypeList(Matrix m) {
		/**
		 * Create an ArrayList of types. the types of id x is add to the list if the
		 * value at position x is different to 0.
		 */
		ArrayList<PlaceType> types = new ArrayList<>();
		for (int i = 0; i < m.sizeX; i++)
			if (m.getMatrix()[i][0] != 0)
				types.add(TypeConfiguration.get(i));
		return types;
	}

	public static HashMap<PlaceType, Double> matrixTotypeMap(Matrix m) {
		/**
		 * Create a HashMap of types. the types of id x is add to the list if the
		 * value at position x is different to 0.
		 */
		HashMap<PlaceType, Double> types = new HashMap<PlaceType, Double>();
		for (int i = 0; i < m.sizeX; i++)
			if (m.getMatrix()[i][0] != 0)
				types.put(TypeConfiguration.get(i), m.getMatrix()[i][1]);
		return types;
	}

	public static String typeToString(PlaceType type) {
		return type.getName();
	}

	public static PlaceType stringToType(String name) {
		return TypeConfiguration.get(name);
	}

	public static int typeToInt(PlaceType type) {
		return type.getId();
	}

	public static PlaceType intToType(int i) {
		return TypeConfiguration.get(i);
	}
}
