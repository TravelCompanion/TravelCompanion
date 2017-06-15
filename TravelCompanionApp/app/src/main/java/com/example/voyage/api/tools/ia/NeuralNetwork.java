package com.example.voyage.api.tools.ia;

import com.example.voyage.api.tools.ia.decition.AbstractDecision;
import com.example.voyage.api.tools.ia.decition.DefaultDecision;
import com.example.voyage.api.tools.ia.learning.DefaultLearning;
import com.example.voyage.api.tools.ia.learning.LearningUnit;
import com.example.voyage.api.tools.math.Matrix;

public abstract class NeuralNetwork {
	public final AbstractDecision decide;
	protected LearningUnit learningUnit;
	protected double step;

	public NeuralNetwork(){
		this.decide = new DefaultDecision();
		this.learningUnit = (LearningUnit) new DefaultLearning();
	}
	public NeuralNetwork(AbstractDecision decide, LearningUnit learningUnit){
		this.decide = decide;
		this.learningUnit = learningUnit;
	}
	
	public abstract Matrix propagate(Matrix matrix);
	public abstract void learn();

	
	
}
