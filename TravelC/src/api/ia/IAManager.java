package api.ia;

import java.util.ArrayList;

import api.data.TheoricPlace;
import api.data.TheoricUser;
import common.type.TypeConfiguration;
import tools.ia.Perceptron;
import tools.ia.decition.SigmoidDecision;
import tools.ia.learning.PerceptronLearning;
import tools.math.Matrix;
import tools.math.compare.CompareUnitDouble;
import tools.math.compare.MathUnitComparator;
import tools.polls.Elector;
import tools.polls.Elegible;
import tools.polls.MajorityJudgmentManager;

public class IAManager {

	public static final double PERCEPTRON_STEP = 0.2;

	// public static Perceptron ia = new Perceptron(new SigmoidDecition(), new
	// PerceptronLearning());
	public static Perceptron ia = new Perceptron(new SigmoidDecision(), new PerceptronLearning());
	private static double lastResult;

	public static ArrayList<CompareUnitDouble<TheoricPlace>> choosePlaces(TheoricUser theoricUser,
			ArrayList<TheoricPlace> places) {
		ia.init(theoricUser.getPreferences(), PERCEPTRON_STEP);
		ArrayList<CompareUnitDouble<TheoricPlace>> tmpList = new ArrayList<CompareUnitDouble<TheoricPlace>>();

		for (TheoricPlace place : places) {
			Matrix m = normalise(place.getTypes(), place.getNote(), place.getNumberOfTypes());

			// tmpList.add(new
			// CompareUnitDouble<TheoricPlace>(ia.propagate(m).getMatrix()[0][0]/place.getNumberOfTypes(),
			// place));
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
		note = note / 5;
		ia.configureLearning(place.getTypes(), lastResult, note);
		ia.learn();
	}

	private static Matrix normalise(Matrix type, double note, int numberOfType) {
		return Matrix.mult(type, note * (TypeConfiguration.number / numberOfType));
	}

	public static double electionVote(TheoricPlace place) {
		Matrix m = Matrix.mult(place.getTypes(), place.getNote());
		return ia.propagate(m).getValue(0, 0);
	}

	public static ArrayList<CompareUnitDouble<TheoricPlace>> friendsChoice(ArrayList<TheoricUser> theoricUsers,
			ArrayList<TheoricPlace> theoricPlaces) {
		ArrayList<Elector> electors = new ArrayList<Elector>();
		electors.addAll(theoricUsers);
		ArrayList<Elegible> elegibles = new ArrayList<Elegible>();
		elegibles.addAll(theoricPlaces);

		ArrayList<CompareUnitDouble<Elegible>> result = MajorityJudgmentManager.election(electors, elegibles);
		ArrayList<CompareUnitDouble<TheoricPlace>> resultPlace = new ArrayList<CompareUnitDouble<TheoricPlace>>();

		for (CompareUnitDouble<Elegible> elegible : result)
			resultPlace.add(
					new CompareUnitDouble<TheoricPlace>(elegible.getValue(), (TheoricPlace) elegible.getElement()));

		return resultPlace;
	}

}
