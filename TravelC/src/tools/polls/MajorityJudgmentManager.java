package tools.polls;

import java.util.ArrayList;

import tools.math.MathComparator;
import tools.list.ListTools;

public class MajorityJudgmentManager {
	private ArrayList<Elector> electors = new ArrayList<Elector>();
	private ArrayList<Elegible> elegibles = new ArrayList<Elegible>();
	
	public int election(ArrayList<Elector> electors,ArrayList<Elegible> elegibles){
		int sx = elegibles.size(),sy = electors.size() ;
		ArrayList<Double>[] votes = ListTools.createTabList(sx);
		
		for(int x = 0; x < sx; x ++)
			for(int y = 0; y < sy;y++){
			votes[sx].add(electors.get(y).vote(elegibles.get(x)));
			}
		
		for(int x = 0; x < sx; x ++)
				votes[sx].sort(MathComparator.getDouble(MathComparator.INF_DOUBLE));
		int med;
		double[] judgments  =  new double[sx];
		for(int x = 0; x < sx; x++){
			judgments[x] = votes[x].get(votes[x].size()/2);
		}
		int elect = -1;
		double max = -1;
		for(int x = 0; x < sx;x++){
			if(judgments[x] > max)
				max = judgments[x];
				elect = x;
		}
	return elect;	
	}
}
