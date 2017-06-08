package tools.ia.learning;

import tools.ia.NeuralNetwork;
import tools.ia.Perceptron;
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
