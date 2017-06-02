package tools.ia;

import java.util.Random;

import tools.ia.decition.AbstractDecition;
import tools.math.Matrix;

public class MultiLayerPerceptron extends NeuralNetwork<MultiLayerPerceptron>{

	public final int entrySize;
	public final int numberLayer;
	private Matrix[] weights;
	
	
	
	public MultiLayerPerceptron(int entrySize, int numberLayer, Matrix[] weights,AbstractDecition decition,double step) {
		super(decition,new MLPLearning());
		this.entrySize = entrySize;
		this.numberLayer = numberLayer;
		this.weights = weights;
		this.step = step;
	}

	public MultiLayerPerceptron(int[] layersSize,AbstractDecition decition,double step){
		super(decition,new MLPLearning());
		this.entrySize = layersSize[0];
		this.numberLayer = layersSize.length;
		this.weights = new Matrix[layersSize.length-1];
		for(int i =0;i < weights.length;i++)
			weights[i-1] = new Matrix(layersSize[i],layersSize[i-1],new Random(),1,0);
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

	public Matrix propagate(Matrix entry) {
		Matrix tmp = entry;
		for(int i = 0; i < weights.length;i++){
			tmp = Matrix.mult(tmp, weights[i]);
			tmp = decide.result(tmp);
		}
		return tmp;
	}

	public Matrix[] propagateWithMemory(Matrix entry){
		Matrix[] memory = new Matrix[numberLayer];
		memory[0] = entry;
			for(int i = 1; i < weights.length;i++)
				memory[i] = decide.result(Matrix.mult(memory[i-1], weights[i]));
			return memory;
	}

	@Override
	public void learn() {
		// TODO Auto-generated method stub
		
	}
	
}
