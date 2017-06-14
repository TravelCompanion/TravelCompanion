package tools.ia;

import java.util.Random;

import tools.ia.decition.AbstractDecision;
import tools.ia.learning.MultiLayerPerceptronLearning;
import tools.math.Matrix;

public class MultiLayerPerceptron extends NeuralNetwork {

	public final int entrySize;
	public final int numberLayer;
	private Matrix[] weights;

	public MultiLayerPerceptron(int entrySize, int numberLayer, Matrix[] weights, AbstractDecision decition,
			double step) {
		super(decition, new MultiLayerPerceptronLearning());
		this.entrySize = entrySize;
		this.numberLayer = numberLayer;
		this.weights = weights;
		this.step = step;
	}

	public MultiLayerPerceptron(int[] layersSize, AbstractDecision decition, double step) {
		super(decition, new MultiLayerPerceptronLearning());
		this.entrySize = layersSize[0];
		this.numberLayer = layersSize.length;
		this.weights = new Matrix[numberLayer - 1];
		Random rand = new Random();
		for (int i = 0; i < weights.length; i++)
			weights[i] = new Matrix(layersSize[i + 1], layersSize[i], rand, 1, 0);
		this.step = step;
	}

	public int getEntrySize() {
		return entrySize;
	}

	public int getNumberLayers() {
		return numberLayer;
	}

	public Matrix[] getWeights() {
		return weights;
	}

	public double getStep() {
		return step;
	}

	public Matrix propagate(Matrix entry) {
		Matrix tmp = entry;
		for (int i = 0; i < weights.length; i++) {
			tmp = Matrix.mult(tmp, weights[i]);
			tmp = decide.decide(tmp);
		}
		return tmp;
	}

	public Matrix[] propagateWithMemory(Matrix entry) {
		Matrix[] memory = new Matrix[numberLayer];
		memory[0] = entry;
		for (int i = 0; i < weights.length; i++) 
			memory[i+1] = decide.decide(Matrix.mult(memory[i], weights[i]));	
		return memory;
	}

	public void configureLearning(Matrix entry, Matrix desired,Matrix[] memory){
		((MultiLayerPerceptronLearning) learningUnit).getParameters(entry, desired,memory);
	}
	
	public void learn() {
		learningUnit.learn(this);
	}

	public void updateWeighs(int i, Matrix matrix) {
		this.weights[i] = Matrix.add(matrix, this.weights[i]);
	}

}
