package sim;

import tools.math.Matrix;

public class Smilblick {

	public static void main(String[] args) {
		Matrix m1 = new Matrix(new double[][]{{3,1},{1,3},{1,2}});
		Matrix m2 = new Matrix(new double[][]{{3,1,2,1},{1,3,2,1}});
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(Matrix.possibleMult(m2, m1));
		System.out.println(Matrix.mult(m2, m1));
	}

}
