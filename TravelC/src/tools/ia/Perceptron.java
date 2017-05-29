package tools.ia;

import tools.ia.decition.AbstractDecition;
import tools.math.Matrix;

public class Perceptron extends NeuralNetwork{
	private Matrix weights;
	public int numberWeights;
	private double step;
	
	public Perceptron(Matrix weigths,double step,AbstractDecition decide){
		this.numberWeights = weigths.sizeY;
		this.weights = weigths;
		this.step = step;
		this.decide = decide;
		this.learningUnit = new PerceptronLearning();
	}
	
	public Matrix propagate(Matrix entry){
		Matrix result = new Matrix(1,1);
		for(int i  = 0; i < numberWeights;i++){
			result =Matrix.mult(entry, weights);
		}
		return result;
	}
	
	public void updateWeights(Matrix m){
		weights = Matrix.add(weights, m);
	}
	public Matrix getWeights() {
		return weights;
	}

	public int getNumberWeights() {
		return numberWeights;
	}

	public double getStep() {
		return step;
	}

}
