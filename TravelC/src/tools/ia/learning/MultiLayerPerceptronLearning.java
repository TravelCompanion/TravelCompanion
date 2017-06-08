package tools.ia.learning;

import tools.ia.MultiLayerPerceptron;
import tools.ia.NeuralNetwork;
import tools.math.Matrix;

public class MultiLayerPerceptronLearning implements LearningUnit{
	private Matrix[] memory;
	private Matrix entry;
	private Matrix desired;
	
	
	
	public void learn(NeuralNetwork network) {
		/*MultiLayerPerceptron mlp = (MultiLayerPerceptron)network;
		int memorySize = memory.length;
		Matrix err = Matrix.mult(memory[memorySize-1], -1);
		err = Matrix.add(desired, err);
		Matrix delta1 = multByDeriv(mlp, err, memorySize, 0);
		for(int k = 1; k < mlp.numberLayer;k++){
		Matrix tmpWeigths = Matrix.mult(mlp.getWeights()[mlp.getWeights().length-1], err);
		Matrix tmpErr = multByDeriv(mlp, tmpWeigths, memorySize, k);
		}*/
	}
	
	public Matrix multByDeriv(NeuralNetwork mlp,Matrix err,int memorySize,int k){
		Matrix deriv = ((MultiLayerPerceptron)mlp).decide.derived(memory[memorySize-1-k]);
		for(int x = 0; x < err.sizeX;x++)
			deriv.getMatrix()[x][0] = err.getMatrix()[x][0]*deriv.getMatrix()[x][0];
		return deriv;
	}
	
	public void getParameters(Matrix entry, Matrix result,Matrix desired) {
		this.entry = entry;
		this.desired = desired;
	}
	
	public Matrix delta(MultiLayerPerceptron multiLayerPerceptron){
		
		return null;
	}

	public Matrix[] getMemory() {
		return memory;
	}

	public Matrix getEntry() {
		return entry;
	}

	public Matrix getDesired() {
		return desired;
	}
	
}
