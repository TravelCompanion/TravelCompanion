package com.example.voyage.api.tools.ia;

import com.example.voyage.api.tools.ia.decition.AbstractDecition;
import com.example.voyage.api.tools.ia.decition.DefaultDecition;
import com.example.voyage.api.tools.math.Matrix;

public abstract class NeuralNetwork<T> {
	public final AbstractDecition decide;
	protected LearningUnit<T> learningUnit;
	
	protected double step;
	@SuppressWarnings("unchecked")
	public NeuralNetwork(){
		this.decide = new DefaultDecition();
		this.learningUnit = (LearningUnit<T>) new DefaultLearning();
	}
	public NeuralNetwork(AbstractDecition decide, LearningUnit<T> learningUnit){
		this.decide = decide;
		this.learningUnit = learningUnit;
	}
	
	public abstract Matrix propagate(Matrix m);
	public abstract void learn();

	
	
}
