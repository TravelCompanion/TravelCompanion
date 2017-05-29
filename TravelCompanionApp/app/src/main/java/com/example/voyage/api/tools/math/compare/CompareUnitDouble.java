package com.example.voyage.api.tools.math.compare;

public class CompareUnitDouble<T> {
	private double value;
	private T element;
	
	public CompareUnitDouble(double value, T element) {
		this.value = value;
		this.element = element;
	}
	
	public double getValue() {
		return value;
	}
	
	public T getElement() {
		return element;
	}

	@Override
	public String toString() {
		return "CompareUnitDouble [value=" + value + ", element=" + element + "]";
	}
	
	
}
