package tools.ia.decition;

import tools.math.Matrix;

public abstract class AbstractDecision {
	public abstract Matrix decide(Matrix result);
	public abstract Matrix derived(Matrix result);
}
