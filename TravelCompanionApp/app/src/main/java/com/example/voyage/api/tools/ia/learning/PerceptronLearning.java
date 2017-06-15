package com.example.voyage.api.tools.ia.learning;

import com.example.voyage.api.tools.ia.NeuralNetwork;
import com.example.voyage.api.tools.ia.Perceptron;
import com.example.voyage.api.tools.math.Matrix;

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
		/**learning function for the perceptron*/
		double error = desired - result;
		((Perceptron)network).updateWeights(Matrix.trans(Matrix.mult(entry, ((Perceptron)network).getStep() * error)));
	}

	public void getParameters(Matrix entry, double result,double desired) {
		/**get the parameters for the perceptron*/
		this.entry = entry;
		this.result = result;
		this.desired = desired;
	}

}
