package tools.math;

import java.util.Random;

public class RandomInt {

	public static int generate() {
		Random rnd = new Random();
		int r = rnd.nextInt();
		r = r < 0 ? -r : r;
		return rnd.nextInt();
	}

	public static int generate(int max) {
		Random rnd = new Random();
		int r = rnd.nextInt();
		r = r < 0 ? -r : r;
		return rnd.nextInt() % (max + 1);
	}

	public static int generate(int max, int min) {
		Random rnd = new Random();
		int r = rnd.nextInt();
		r = r < 0 ? -r : r;
		return (rnd.nextInt() % ((max + 1) - min)) + min;
	}

	public static int generate(Random rnd) {
		int r = rnd.nextInt();
		r = r < 0 ? -r : r;
		return rnd.nextInt();
	}

	public static int generate(Random rnd, int max) {
		int r = rnd.nextInt();
		r = r < 0 ? -r : r;
		return r % (max + 1);
	}

	public static int generate(Random rnd, int max, int min) {
		int r = rnd.nextInt();
		r = r < 0 ? -r : r;
		return (rnd.nextInt() % ((max + 1) - min)) + min;
	}
}
