package tools.ia;

import tools.math.Matrix;

public class MLPLearning implements LearningUnit{
	private Matrix[] memory;
	private Matrix entry;
	private Matrix result;
	private Matrix desired;
	
	
	
	public void learn(NeuralNetwork network) {
		MultiLayerPerceptron mlp = (MultiLayerPerceptron)network;
		
	}
	
	
	
	public void getParameters(Matrix entry, Matrix result,Matrix desired) {
		this.entry = entry;
		this.result = result;
		this.desired = desired;
	}
	
	public Matrix delta(MultiLayerPerceptron multiLayerPerceptron){
		
		return null;
	}
	
}
