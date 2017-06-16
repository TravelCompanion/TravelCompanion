package sim;

import java.sql.SQLException;
import java.util.ArrayList;

import common.type.TypeConfiguration;
import model.Monument;
import persistence.PersistenceData;
import tools.math.MathTools;
import tools.math.Matrix;
import tools.parse.StringParser;

public class GetAllPref {
	public static void main(String[] args) {
		PersistenceData persistenceData = new PersistenceData("root", "", "release1");
		TypeConfiguration.getConfig();
		try {
			ArrayList<String> strings = persistenceData.allMonumentType();
			ArrayList<Matrix> matrixs = new ArrayList<Matrix>();
			for (String string : strings) {
				ArrayList<String> tmp = StringParser.sliceLine(string, ',');
				Matrix matrix = new Matrix(TypeConfiguration.number, 1);
				for (String line : tmp)
					matrix.setValue(TypeConfiguration.get(line).getId(), 0, 1);
				matrixs.add(matrix);
			}
			System.out.println(matrixs);
			Matrix weights = new Matrix(14, 14);
			Matrix weights2 = new Matrix(14, 14);
			int[] typeNum = new int[14];
			for (int i = 0; i < typeNum.length; i++)
				typeNum[i] = 0;
			Matrix tmp;
			for (int i = 0; i < matrixs.size(); i++) {
				tmp = matrixs.get(i);
				for (int j = 0; j < TypeConfiguration.number; j++) {
					if (tmp.getValue(j, 0) == 1) {
						typeNum[j] += 1;
						for (int k = 0; k < TypeConfiguration.number; k++)
							if (!(j == k)){
								weights.setValue(j, k, weights.getValue(j, k) + tmp.getValue(k, 0));
								weights2.setValue(j, k, weights2.getValue(j, k) + tmp.getValue(k, 0)-1);
							}
					}
				}
			}

			System.out.println(weights);
			for (int i = 0; i < TypeConfiguration.number; i++)
				for (int j = 0; j < TypeConfiguration.number; j++)
					if (i == j){
						weights.setValue(i, j, 1);
					}
					else{
						weights.setValue(i, j,weights.getValue(i, j) / typeNum[i]);
						weights2.setValue(i, j,weights2.getValue(i, j) / typeNum[i]);
						
					}
			System.out.println(weights);
			Matrix w = Matrix.add(weights, weights2);
			System.out.println(w);
			
			for (int i = 0; i < TypeConfiguration.number; i++)
				for (int j = 0; j < TypeConfiguration.number; j++)
					w.setValue(i, j,MathTools.roundAt( w.getValue(i, j), 3));
			System.out.println(w);
			
			String line = w.toLog();
			ArrayList<String> lines = new ArrayList<String>();
			lines.add(line);
			
			StringParser.writeData("./data/typesMatrix.txt",lines);
			
			
			
			/*for (int i = 0; i < TypeConfiguration.number; i++) {
				double sum = 0;
				for (int j = 0; j < TypeConfiguration.number; j++)
					sum += weights.getValue(i, j);
				for (int j = 0; j < TypeConfiguration.number; j++)
					weights.setValue(i, j, weights.getValue(i, j) / sum);
			}*/
			
			/*System.out.println(weights);
			for (int i = 0; i < TypeConfiguration.number; i++) {
				double sum = 0;
				for (int j = 0; j < TypeConfiguration.number; j++) {
					sum += weights.getValue(i, j);
				}
				System.out.println(sum);
			}*/

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
