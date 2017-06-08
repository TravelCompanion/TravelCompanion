package sim;

import java.util.ArrayList;

import common.type.TypeConfiguration;
import model.Monument;
import model.Utilisateur;
import tools.ia.Perceptron;
import tools.ia.decition.DefaultDecision;
import tools.ia.learning.PerceptronLearning;
import tools.math.Matrix;
import tools.parse.StringParser;

public class Propag {

	private static Perceptron perceptron = new Perceptron(new DefaultDecision(), new PerceptronLearning());
	// Perceptron perceptron = new Perceptron(new SigmoidDecition(), new
	// PerceptronLearning());

	private static Matrix toType(String line) {
		Matrix matrix = new Matrix(14, 1);
		ArrayList<String> lines = StringParser.sliceLine(line, ',');
		// découpage de ligne en liste String à l'aide du séparateur indiqué
		for (String type : lines)
			matrix.setValue(TypeConfiguration.get(type).getId(), 0, 1);
		// affectation des valeurs de la matrice
		return matrix;
	}

	private static Matrix toPref(String line) {
		Matrix matrix = new Matrix(1, 14);
		ArrayList<String> lines = StringParser.sliceLine(line, ',');
		// découpage de ligne en liste String à l'aide du séparateur indiqué
		for (int i = 0; i < 14; i++)
			matrix.setValue(0, i, Double.parseDouble(lines.get(i)));
		// affectation des valeurs de la matrice
		return matrix;
	}

	public static void progation(Utilisateur user, ArrayList<Monument> monuments) {
		TypeConfiguration.getConfig();
		// initialisation de la classe TypeConfiguration
		System.out.println("number of types : " + TypeConfiguration.number);
		// doit être différent de 0
		Matrix userPref = toPref(user.getPreferences());
		// mise sous forme de matrice colone des préférence de l'utilisateur
		Matrix entry ;//déclaration de la matrice d'entré
		for(Monument monument : monuments){
			entry = toType(monument.getType());
			//entry affeceté avec la matrice des type du monument
			entry = Matrix.mult(entry, monument.getNote());
			// multiplication de l'entré par la note
			perceptron.init(userPref, 0.2);
			//initialistion des paramètre du perceptron
			System.out.println(perceptron.propagate(entry)+" "+monument.getName_monument());
			//propagation
		}
	}
}
