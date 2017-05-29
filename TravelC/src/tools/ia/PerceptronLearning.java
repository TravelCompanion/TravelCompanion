package tools.ia;

import tools.math.Matrix;

public class PerceptronLearning implements LearningUnit {
	private Matrix entry;
	private double result;
	private double desired;

	public PerceptronLearning() {}
	
	public PerceptronLearning(Matrix entry, double result, double desired) {
		this.entry = entry;
		this.result = result;
		this.desired = desired;
	}

	public void learn(NeuralNetwork network) {
		Perceptron perceptron = (Perceptron) network;
		double error = desired - result;
		perceptron.updateWeights(Matrix.mult(entry, perceptron.getStep() * error));
	}

	public void getParameters(Matrix entry, double result,double desired) {
		this.entry = entry;
		this.result = result;
		this.desired = desired;
	}

}
