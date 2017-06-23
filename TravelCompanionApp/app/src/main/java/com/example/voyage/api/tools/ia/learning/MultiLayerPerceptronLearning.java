package com.example.voyage.api.tools.ia.learning;

import com.example.voyage.api.tools.ia.MultiLayerPerceptron;
import com.example.voyage.api.tools.ia.NeuralNetwork;
import com.example.voyage.api.tools.math.Matrix;

public class MultiLayerPerceptronLearning implements LearningUnit {
	private Matrix[] memory;
	private Matrix entry;
	private Matrix desired;

	public void learn(NeuralNetwork network) {
		// Erruer Globale
		Matrix errorG = globalError((MultiLayerPerceptron) network);
		System.out.println(errorG);
		localErrors((MultiLayerPerceptron)network, errorG);

	}

	private void localErrors(MultiLayerPerceptron network, Matrix globalError) {
		int numberWeights = network.getWeights().length;
		Matrix derived;
		Matrix oldError;
		Matrix delta;
		Matrix newError = globalError;

		for (int i = 1; i < numberWeights; i++) {
			oldError = newError;
			derived = network.decide.derived(memory[memory.length - 1 - i]);
			delta = Matrix.mult(network.getWeights()[numberWeights - i], oldError);
			System.out.println(network.getWeights()[numberWeights - i]);
			System.out.println(oldError);
			newError = Matrix.mult(derived, delta);
			System.out.println(derived);
			System.out.println(delta);
			System.out.println(newError);
			System.out.println(memory[memory.length - 2 - i]);
			network.updateWeighs(numberWeights - i,
					Matrix.mult(Matrix.mult(memory[memory.length - 2 - i], newError), network.getStep()));

		}

	}

	private Matrix globalError(MultiLayerPerceptron network) {
		Matrix error = Matrix.add(desired, Matrix.mult(memory[memory.length - 1], -1));
		Matrix derivedOutput = network.decide.derived(memory[memory.length - 1]);
		System.out.println(error);
		System.out.println(derivedOutput);
		return Matrix.mult( Matrix.trans(derivedOutput),error);
	}

	public Matrix multByDeriv(NeuralNetwork mlp, Matrix err, int memorySize, int k) {
		Matrix deriv = ((MultiLayerPerceptron) mlp).decide.derived(memory[memorySize - 1 - k]);
		for (int x = 0; x < err.sizeX; x++)
			deriv.getMatrix()[x][0] = err.getMatrix()[x][0] * deriv.getMatrix()[x][0];
		return deriv;
	}

	public void getParameters(Matrix entry, Matrix result, Matrix desired) {
		this.entry = entry;
		this.desired = desired;
	}

	public Matrix delta(MultiLayerPerceptron multiLayerPerceptron) {

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

	public void getParameters(Matrix entry, Matrix desired, Matrix[] memory) {
		this.entry =entry;
		this.desired = desired;
		this.memory = memory;
	}

}
