package tools.ia;

import tools.ia.decition.AbstractDecition;
import tools.ia.decition.DefaultDecition;
import tools.math.Matrix;

public abstract class NeuralNetwork<T> {
	public final AbstractDecition decide;
	protected LearningUnit<T> learningUnit;
	
	protected double step;
	public NeuralNetwork(){
		this.decide = new DefaultDecition();
		this.learningUnit = (LearningUnit) new DefaultLearning();
	}
	public NeuralNetwork(AbstractDecition decide, LearningUnit learningUnit){
		this.decide = decide;
		this.learningUnit = learningUnit;
	}
	
	public abstract Matrix propagate(Matrix m);
	public abstract void learn();

	
	
}
