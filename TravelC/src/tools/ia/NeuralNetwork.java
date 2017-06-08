package tools.ia;

import tools.ia.decition.AbstractDecision;
import tools.ia.decition.DefaultDecision;
import tools.ia.learning.DefaultLearning;
import tools.ia.learning.LearningUnit;
import tools.math.Matrix;

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
