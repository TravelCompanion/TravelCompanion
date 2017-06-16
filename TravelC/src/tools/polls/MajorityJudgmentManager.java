package tools.polls;

import java.util.ArrayList;
import java.util.Collections;

import tools.math.Matrix;
import tools.math.compare.CompareUnitDouble;
import tools.math.compare.MathComparator;

public class MajorityJudgmentManager {

	private static Matrix getVotes(ArrayList<Elector> electors, ArrayList<Elegible> elegibles) {
		Matrix votes = new Matrix(electors.size(), elegibles.size());
		for (int x = 0; x < votes.sizeX; x++)
			for (int y = 0; y < votes.sizeY; y++)
				votes.setValue(x, y, electors.get(x).vote(elegibles.get(y)));
		return votes;
	}

	private static ArrayList<CompareUnitDouble<Elegible>> getResluts(ArrayList<Elegible> elegibles, Matrix votes) {
		ArrayList<CompareUnitDouble<Elegible>> compareEligible = new ArrayList<CompareUnitDouble<Elegible>>();
		ArrayList<Double> candidatVote = new ArrayList<Double>();
		for (int y = 0; y < votes.sizeY; y++) {
			for (int x = 0; x < votes.sizeX; x++)
				candidatVote.add(votes.getValue(x, y));
			candidatVote.sort(MathComparator.getByNameDouble("<"));
			compareEligible.add(
					new CompareUnitDouble<Elegible>(candidatVote.get((candidatVote.size() + 1) / 2), elegibles.get(y)));
		}
		return compareEligible;
	}

	public static ArrayList<CompareUnitDouble<Elegible>> election(ArrayList<Elector> electors, ArrayList<Elegible> elegibles) {
		Matrix votes = getVotes(electors, elegibles);
		ArrayList<CompareUnitDouble<Elegible>> compareEligible = getResluts(elegibles, votes);
		//compareEligible.sort(MathUnitComparator.getByNameDouble("<"));
		Collections.sort(compareEligible);
		return compareEligible;
	}
}
