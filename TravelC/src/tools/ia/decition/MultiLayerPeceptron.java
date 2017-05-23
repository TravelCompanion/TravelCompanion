package tools.ia.decition;

import tools.math.Matrix;

public class MultiLayerPeceptron {
	private AbstractDecition decide;
	
	private Matrix[] weights;
	private int numberLayer;
	
	public MultiLayerPeceptron(AbstractDecition decide, Matrix[] weights) {
		this.decide = decide;
		this.weights = weights;
		this.numberLayer = weights.length;
	}
	
	public Matrix propagate(Matrix entry){
		Matrix result = entry;
		for(int i = 0; i < numberLayer;i++){
			result = Matrix.mult(result,weights[i]);
			for(int x = 0; x < result.getSizeX();x++)
				for(int y = 0; y <result.getSizeY();y++)
					result.getMatrix()[x][y] = decide.result(result.getMatrix()[x][y]);
		}
		return entry;
	}
	
	
}
