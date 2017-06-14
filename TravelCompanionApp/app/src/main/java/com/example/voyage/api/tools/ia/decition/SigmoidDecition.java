package com.example.voyage.api.tools.ia.decition;

import com.example.voyage.api.tools.math.Matrix;

public class SigmoidDecition extends AbstractDecition{
	public Matrix result(Matrix result){
		/*Matrix tmp = result;
		for(int x = 0; x < result.sizeX;x++)
			for(int y = 0; y < result.sizeY;y++)
		tmp.getMatrix()[x][y] = 1/(1+Math.exp(-result.getMatrix()[x][y]));*/
		return result;
	}

	public Matrix derived(Matrix result) {
		Matrix tmp = result;
		double tmpval;
		for(int x = 0; x < result.sizeX;x++)
			for(int y = 0; y < result.sizeY;y++){
			tmpval = tmp.getMatrix()[x][y];
			tmp.getMatrix()[x][y] = tmpval*(1 - tmpval);
		}
		return tmp;
	}
}
