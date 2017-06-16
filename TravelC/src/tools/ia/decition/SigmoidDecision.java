package tools.ia.decition;

import tools.math.Matrix;

public class SigmoidDecision extends AbstractDecision {
	private double curve;
	public SigmoidDecision() {
		this(1);
	}

	public SigmoidDecision(double curve) {
		this.curve = curve;
	}

	public Matrix decide(Matrix result) {
		Matrix tmp = result;
		for (int x = 0; x < result.sizeX; x++)
			for (int y = 0; y < result.sizeY; y++)
				tmp.getMatrix()[x][y] = 1 / (1 + Math.exp(-result.getValue(x, y)*curve));
		// return result;
		return tmp;
	}

	public Matrix derived(Matrix result) {
		Matrix tmp = result;
		double tmpval;
		for (int x = 0; x < result.sizeX; x++)
			for (int y = 0; y < result.sizeY; y++) {
				tmpval = tmp.getValue(x, y);
				tmp.setValue(x, y, curve*tmpval * (1 - tmpval)); 
			}
		return tmp;
	}
}
