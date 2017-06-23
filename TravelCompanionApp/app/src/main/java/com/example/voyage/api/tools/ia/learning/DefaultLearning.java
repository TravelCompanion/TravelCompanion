package com.example.voyage.api.tools.ia.learning;

import com.example.voyage.api.tools.ia.NeuralNetwork;

public class DefaultLearning implements LearningUnit{

	
	public void learn(NeuralNetwork network) {
		network.learn();
	}

}
