package tools.ia.learning;

import tools.ia.NeuralNetwork;

public class DefaultLearning implements LearningUnit{

	
	public void learn(NeuralNetwork network) {
		network.learn();
	}

}
