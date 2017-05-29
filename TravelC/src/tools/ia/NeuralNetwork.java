package tools.ia;

import tools.ia.decition.AbstractDecition;
import tools.math.Matrix;

public abstract class NeuralNetwork {
	protected AbstractDecition decide;
	protected LearningUnit learningUnit;
	
	protected double step;
	
	public abstract Matrix propagate(Matrix m);
	public void learn(){
		learningUnit.learn(this);
	}
}
