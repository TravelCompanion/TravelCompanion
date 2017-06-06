package tools.ia;

import tools.ia.decition.AbstractDecition;
import tools.math.Matrix;

public class Perceptron extends NeuralNetwork{
	@Override
	public String toString() {
		return "Perceptron [weights=" + weights + ", numberWeights=" + numberWeights + ", step=" + step + "]";
	}

	private Matrix weights;
	public int numberWeights;
	private double step;
	
	public Perceptron(AbstractDecition decide,LearningUnit learningUnit){
		super(decide,learningUnit);
	}
		
	public Perceptron(Matrix weigths,double step,AbstractDecition decide){
		super(decide,new PerceptronLearning());
		this.numberWeights = weigths.sizeY;
		this.weights = weigths;
		this.step = step;
		
	}
	
	public Matrix propagate(Matrix entry){
		/**propagate the entry in the perceptron*/
		Matrix result = new Matrix(1,1);
			result = Matrix.mult(entry, weights);
		return decide.result(result);
	}
	
	public void configureLearning(Matrix entry, double result,double desired){
		((PerceptronLearning) learningUnit).getParameters(entry, result, desired);
	}
	
	public void init(Matrix weights, double step){
		this.numberWeights = weights.sizeY;
		this.weights = weights;
		this.step = step;
	}
	
	public void learn(){
		learningUnit.learn(this);
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
