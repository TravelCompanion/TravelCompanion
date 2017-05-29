package com.example.voyage.api.tools.math.simulation;

import com.example.voyage.api.tools.math.Matrix;

public class MatrixSim {
	public static void main(String args[]){
		Matrix m1 = new Matrix(new double[][]{{1,2,5,6},{1,2,5,6},{6,23,5,6},{12,34,5,54},{3,2,345,34}});
		System.out.println(m1);
		System.out.println("size x : "+m1.getSizeX()+" size y : "+m1.getSizeY());
	}
}
