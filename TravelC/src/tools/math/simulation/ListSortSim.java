package tools.math.simulation;

import java.util.ArrayList;
import java.util.Random;

import tools.list.ListTools;
import tools.math.random.RandomInt;

public class ListSortSim {
	public static void main(String[] args){
		ArrayList<Double> ints = new ArrayList<Double>();
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
			ints.add((double)RandomInt.generate(rand,100));
		System.out.println(ints);
		//ints.sort(MathComparator.getByNameDouble("<"));
		ListTools.sortListByNumberDesc(ints);
		System.out.println(ints);
		ListTools.sortListByNumberAsc(ints);
		System.out.println(ints);
	}
}
