package tools.ia.decition;

import tools.math.random.RandomDouble;

public class RandomNotation extends AbstractDecition{
	public double result(double entry){
		return RandomDouble.generate(entry*95,entry*1.05);
	}
}
