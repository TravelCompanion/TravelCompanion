package api.ia;

import java.util.ArrayList;

import api.data.TheoricPlace;
import api.data.TheoricUser;
import tools.ia.Perceptron;
import tools.ia.PerceptronLearning;
import tools.ia.decition.SigmoidDecition;
import tools.math.Matrix;
import tools.math.compare.CompareUnitDouble;
import tools.math.compare.MathUnitComparator;

public class IAManager {

	public static final double PERCEPTRON_STEP = 0.2;

	//public static Perceptron ia = new Perceptron(new SigmoidDecition(), new PerceptronLearning());
	public static Perceptron ia = new Perceptron(new SigmoidDecition(), new PerceptronLearning());
	private static double lastResult;

	public static ArrayList<CompareUnitDouble<TheoricPlace>> choosePlaces(TheoricUser theoricUser,
			ArrayList<TheoricPlace> places) {
		ia.init(theoricUser.getPreferences(), PERCEPTRON_STEP);
		ArrayList<CompareUnitDouble<TheoricPlace>> tmpList = new ArrayList<CompareUnitDouble<TheoricPlace>>();
		
		for (TheoricPlace place : places)
		{	
		Matrix m =Matrix.mult(place.getTypes(), place.getNote());
		
		//tmpList.add(new CompareUnitDouble<TheoricPlace>(ia.propagate(m).getMatrix()[0][0]/place.getNumberOfTypes(), place));
		tmpList.add(new CompareUnitDouble<TheoricPlace>(ia.propagate(m).getMatrix()[0][0], place));
		
		}
		tmpList.sort(MathUnitComparator.getByNameDouble("<"));
		return tmpList;
	}

	public static TheoricPlace selectPlace(int posPlace, ArrayList<CompareUnitDouble<TheoricPlace>> list) {
		lastResult = list.get(posPlace).getValue();
		return list.get(posPlace).getElement();
	}

	public static void learn(TheoricUser theoricUser, TheoricPlace place, double note) {
		note = note/5;
		ia.configureLearning(place.getTypes(), lastResult, note);
		ia.learn();
	}


	
}
