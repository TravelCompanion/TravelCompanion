package tools.math;

import java.util.Random;

public class RandomDouble {
	public static double generate() {
		Random rand = new Random();
		return rand.nextDouble();
	}

	public static double generate(double max) {
		Random rand = new Random();
		return rand.nextDouble() % (max + 1);
	}

	public static double generate(double max, double min) {
		Random rand = new Random();
		return rand.nextDouble() % (max + 1 - min) + min;
	}

	public static double generate(Random rand) {
		return rand.nextDouble();
	}

	public static double generate(Random rand, double max) {
		return rand.nextDouble() % (max + 1);
	}

	public static double generate(Random rand, double max, double min) {
		return rand.nextDouble() % (max + 1 - min) + min;
	}
	
	public static double generateCentered(double medium,double range){
		Random rand = new Random();
		double min = medium - (range/2); 
		double max = medium + (range/2); 
		return generate(rand,max,min);
	}
	
	public static double generateCentered(Random rand, double medium,double range){
		double min = medium - (range/2); 
		double max = medium + (range/2); 
		return generate(rand,max,min);
	}
}
