package com.example.voyage.api.tools.ia.decition;

import com.example.voyage.api.tools.math.Matrix;

public class DefaultDecision extends AbstractDecision{

	public Matrix decide(Matrix result) {
	return result;
	}

	public Matrix derived(Matrix result) {
		return result;
	}

}
