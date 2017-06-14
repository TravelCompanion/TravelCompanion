package tools.ia;

import tools.ia.decition.AbstractDecision;
import tools.ia.learning.LearningUnit;
import tools.ia.learning.PerceptronLearning;
import tools.math.Matrix;

public class Perceptron extends NeuralNetwork{

	private Matrix weights;
	public int numberWeights;
	
	public Perceptron(AbstractDecision decide,LearningUnit learningUnit){
		super(decide,learningUnit);
	}
		
	public Perceptron(Matrix weigths,double step,AbstractDecision decide){
		super(decide,new PerceptronLearning());
		this.numberWeights = weigths.sizeY;
		this.weights = weigths;
		this.step = step;
		
	}
	
	public Matrix propagate(Matrix entry){
		/**propagate the entry in the perceptron*/
		Matrix result = new Matrix(1,1);
			result = Matrix.mult(entry, weights);
		return decide.decide(result);
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
	
	public void updateWeights(Matrix matrix){
		this.weights = Matrix.add(weights, matrix);
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

	public String toString() {
		return "Perceptron [weights=" + weights + ", numberWeights=" + numberWeights + ", step=" + step + "]";
	}
	
}
