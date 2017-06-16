package api.ia;

import java.util.ArrayList;

import api.data.TheoricDataBase;
import api.data.TheoricPlace;
import api.data.TheoricUser;

import common.type.TypeConfiguration;

import tools.ia.Perceptron;
import tools.ia.decition.SigmoidDecision;
import tools.ia.learning.PerceptronLearning;
import tools.math.MathTools;
import tools.math.Matrix;
import tools.math.compare.CompareUnitDouble;
import tools.math.compare.MathUnitComparator;
import tools.polls.Elector;
import tools.polls.Elegible;
import tools.polls.MajorityJudgmentManager;

public class IAManager {

	public static final double PERCEPTRON_STEP = 0.2;
	public static final double PERCEPTRON_CURVE = 0.1;                                                                              
	public static Perceptron ia = new Perceptron(new SigmoidDecision(PERCEPTRON_CURVE), new PerceptronLearning());
	private static double lastResult;

	public static ArrayList<CompareUnitDouble<TheoricPlace>> choosePlaces() {
		return choosePlaces(TheoricDataBase.mainUser, TheoricDataBase.places);
	}

	public static ArrayList<CompareUnitDouble<TheoricPlace>> choosePlaces(TheoricUser theoricUser,
			ArrayList<TheoricPlace> places) {
		ia.init(theoricUser.getPreferences(), PERCEPTRON_STEP);
		ArrayList<CompareUnitDouble<TheoricPlace>> tmpList = new ArrayList<CompareUnitDouble<TheoricPlace>>();

		for (TheoricPlace place : places) {
			Matrix m = normalise(place.getTypes(), place.getNumberOfTypes());
			tmpList.add(new CompareUnitDouble<TheoricPlace>(
					ia.propagate(Matrix.mult(m, place.getNote())).getValue(0, 0), place));
		}

		tmpList.sort(MathUnitComparator.getByNameDouble("<"));
		return tmpList;
	}

	public static double dist(Matrix m1, Matrix m2) {
		double sum = 0;
		for (int i = 0; i < m1.sizeX; i++)
			sum += Math.pow(m1.getValue(i, 0) - m2.getValue(i, 0), 2.0);
		return Math.sqrt(sum);
	}

	public static ArrayList<CompareUnitDouble<TheoricPlace>> choosePlaceWithFriends(TheoricUser user,
			ArrayList<TheoricUser> users, ArrayList<TheoricPlace> places, double lambda) {
		CompareUnitDouble<TheoricPlace> friendsChoice = friendsChoice(users, places).get(0);
		ia.init(user.getPreferences(), PERCEPTRON_STEP);
		ArrayList<CompareUnitDouble<TheoricPlace>> tmpList = new ArrayList<CompareUnitDouble<TheoricPlace>>();
		double d;
		Matrix m;
		for (TheoricPlace place : places) {
			m = place.getTypes();
			d = dist(m, friendsChoice.getElement().getTypes());
			m = Matrix.add(Matrix.mult(m, place.getNote()), Matrix.mult(m, -d * lambda));
			m = normalise(m, place.getNumberOfTypes());
			tmpList.add(new CompareUnitDouble<TheoricPlace>(ia.propagate(m).getValue(0, 0), place));
		}

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
		for (int i = 0; i < ia.numberWeights; i++)
			ia.getWeights().setValue(0, i, MathTools.roundAt(ia.getWeights().getValue(0, i), 3));
		theoricUser.updatePref(ia.getWeights());
	}

	public static void shortLearn(TheoricUser theoricUser, TheoricPlace place) {
		ia.configureLearning(place.getTypes(), 0, 0.1);
		ia.learn();
		for (int i = 0; i < ia.numberWeights; i++)
			ia.getWeights().setValue(0, i, MathTools.roundAt(ia.getWeights().getValue(0, i), 3));
		theoricUser.updatePref(ia.getWeights());
	}

	private static Matrix normalise(Matrix type, int numberOfType) {
		return Matrix.mult(type, (TypeConfiguration.number / numberOfType));
	}

	public static double electionVote(TheoricPlace place, TheoricUser theoricUser) {
		Matrix m = normalise(place.getTypes(), place.getNumberOfTypes());
		ia.init(theoricUser.getPreferences(), PERCEPTRON_STEP);
		
		return ia.propagate(Matrix.mult(m, place.getNote())).getValue(0, 0);
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
