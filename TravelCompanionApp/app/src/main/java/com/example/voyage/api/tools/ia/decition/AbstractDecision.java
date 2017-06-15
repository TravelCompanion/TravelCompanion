package com.example.voyage.api.tools.ia.decition;

import com.example.voyage.api.tools.math.Matrix;

public abstract class AbstractDecision {
	public abstract Matrix decide(Matrix result);
	public abstract Matrix derived(Matrix result);
}
