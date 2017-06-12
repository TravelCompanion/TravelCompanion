package com.example.voyage.api.tools.ia;

import com.example.voyage.api.tools.math.Matrix;

public class PerceptronLearning implements LearningUnit<Perceptron> {
	private Matrix entry;
	private double result;
	private double desired;

	public PerceptronLearning() {}
	
	public PerceptronLearning(Matrix entry, double result, double desired) {
		this.entry = entry;
		this.result = result;
		this.desired = desired;
	}

	public void learn(Perceptron network) {
		/**learning function for the perceptron*/
		double error = desired - result;
		network.updateWeights(Matrix.trans(Matrix.mult(entry, network.getStep() * error)));
	}

	public void getParameters(Matrix entry, double result,double desired) {
		/**get the parameters for the perceptron*/
		this.entry = entry;
		this.result = result;
		this.desired = desired;
	}

}
