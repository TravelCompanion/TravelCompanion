package com.example.voyage.api.tools.ia.decition;

import com.example.voyage.api.tools.math.Matrix;

public abstract class AbstractDecition {
	public abstract Matrix result(Matrix result);
	public abstract Matrix derived(Matrix result);
}
