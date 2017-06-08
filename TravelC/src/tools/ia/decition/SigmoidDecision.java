package tools.ia.decition;

import tools.math.Matrix;

public class SigmoidDecision extends AbstractDecision{
	public Matrix decide(Matrix result){
		Matrix tmp = result;
		for(int x = 0; x < result.sizeX;x++)
			for(int y = 0; y < result.sizeY;y++)
		tmp.getMatrix()[x][y] = 1/(1+Math.exp(-result.getMatrix()[x][y]));
		//return result;
		return tmp;
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
