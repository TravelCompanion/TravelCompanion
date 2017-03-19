package tools.math;

import java.util.ArrayList;
import java.util.List;

import tools.math.MathTools.Counter;

public class CounterSim {

	public static void main(String[] args) {
		MathTools.Counter counter = new MathTools.Counter(3, 3);
		List<Integer[]> list = new ArrayList<Integer[]>();
		list = counter.getComb();
		String el = ""; 
		for(int i = 0; i < list.size();i++)
		{
			for(int j = 0; j < list.get(i).length;j++)
				el +=" "+list.get(i)[j];
			el += "\n";
		}
		System.out.println("p :" +el);
	}

}
