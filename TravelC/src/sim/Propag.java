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
		// d�coupage de ligne en liste String � l'aide du s�parateur indiqu�
		for (String type : lines)
			matrix.setValue(TypeConfiguration.get(type).getId(), 0, 1);
		// affectation des valeurs de la matrice
		return matrix;
	}

	private static Matrix toPref(String line) {
		Matrix matrix = new Matrix(1, 14);
		ArrayList<String> lines = StringParser.sliceLine(line, ',');
		// d�coupage de ligne en liste String � l'aide du s�parateur indiqu�
		for (int i = 0; i < 14; i++)
			matrix.setValue(0, i, Double.parseDouble(lines.get(i)));
		// affectation des valeurs de la matrice
		return matrix;
	}

	public static void progation(Utilisateur user, ArrayList<Monument> monuments) {
		TypeConfiguration.getConfig();
		// initialisation de la classe TypeConfiguration
		System.out.println("number of types : " + TypeConfiguration.number);
		// doit �tre diff�rent de 0
		Matrix userPref = toPref(user.getPreferences());
		// mise sous forme de matrice colone des pr�f�rence de l'utilisateur
		Matrix entry ;//d�claration de la matrice d'entr�
		for(Monument monument : monuments){
			entry = toType(monument.getType());
			//entry affecet� avec la matrice des type du monument
			entry = Matrix.mult(entry, monument.getNote());
			// multiplication de l'entr� par la note
			perceptron.init(userPref, 0.2);
			//initialistion des param�tre du perceptron
			System.out.println(perceptron.propagate(entry)+" "+monument.getName_monument());
			//propagation
		}
	}
}
