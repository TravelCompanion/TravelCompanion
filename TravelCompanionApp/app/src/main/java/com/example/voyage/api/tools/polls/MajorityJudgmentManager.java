package com.example.voyage.api.tools.polls;

import java.util.ArrayList;

import com.example.voyage.api.tools.list.ListTools;
import com.example.voyage.api.tools.math.compare.MathComparator;

public class MajorityJudgmentManager {
	
	public int election(ArrayList<Elector> electors,ArrayList<Elegible> elegibles){
		int sx = elegibles.size(),sy = electors.size() ;
		ArrayList<Double>[] votes = ListTools.createTabList(sx);
		
		for(int x = 0; x < sx; x ++)
			for(int y = 0; y < sy;y++){
			votes[sx].add(electors.get(y).vote(elegibles.get(x)));
			}
		
		for(int x = 0; x < sx; x ++)
				votes[sx].sort(MathComparator.getByNameDouble("<"));
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
