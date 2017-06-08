package sim;

import tools.ia.MultiLayerPerceptron;
import tools.ia.decition.SigmoidDecision;
import tools.math.Matrix;

public class Test3 {
	public static void main(String[] args){
		MultiLayerPerceptron multiLayerPerceptron = new MultiLayerPerceptron(new int []{5,4,2,6}, new SigmoidDecision(),0.2);

		Matrix entry = new Matrix(5,1);
		entry.setValue(0, 0, 0.5);
		entry.setValue(1, 0, 0.2);
		entry.setValue(2, 0, 0.1);
		entry.setValue(3, 0, 0.7);
		entry.setValue(4, 0, 0.3);
		
		Matrix[] memory = multiLayerPerceptron.propagateWithMemory(entry);
		
		Matrix desired = new Matrix(6,1);
		desired.setValue(0, 0, 0.1);
		desired.setValue(1, 0, 0.7);
		desired.setValue(2, 0, 0.9);
		desired.setValue(3, 0, 0.6);
		desired.setValue(4, 0, 0.4);
		desired.setValue(5, 0, 0.8);
		
		multiLayerPerceptron.configureLearning(entry, desired, memory);
		multiLayerPerceptron.learn();
		
	}
}
