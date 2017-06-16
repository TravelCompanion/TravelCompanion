package sim;

import java.util.ArrayList;

import tools.math.Matrix;
import tools.parse.StringParser;

public class TypeMatrixReader {
	public static void main(String[] args) {
		ArrayList<String> lines = StringParser.readData("./data/typesMatrix.txt");
		Matrix matrix = StringParser.generateFromParse(new Matrix(1,1), lines.get(0), ',');
		System.out.println(matrix);
	}
}
