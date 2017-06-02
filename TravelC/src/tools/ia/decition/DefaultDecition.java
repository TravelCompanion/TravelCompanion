package tools.ia.decition;

import tools.math.Matrix;

public class DefaultDecition extends AbstractDecition{

	public Matrix result(Matrix result) {
		return Matrix.add(Matrix.kmatrix(result.sizeX, result.sizeY, -1),result);
	}

	public Matrix derived(Matrix result) {
		return result;
	}

}
