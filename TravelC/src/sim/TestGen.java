package sim;

import java.util.Random;

import tools.math.MathTools;
import tools.math.random.RandomDouble;

public class TestGen {
	public static void main(String[] args)
	{
		Random rand = new Random();
		
		double[]  tab = new double[14];
		for(int i =0; i < 14;i++)
			tab[i] = MathTools.roundAt(RandomDouble.generate(rand, 1,0),2);	
		String elt = ""+tab[0];
		for(int i =1; i < 14;i++)
			elt+=","+tab[i];
		System.out.println(elt);
	}
}
