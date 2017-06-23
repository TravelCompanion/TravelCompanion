package com.example.voyage.api.tools.math.compare;

import android.support.annotation.NonNull;

public class CompareUnitDouble<T> implements  Comparable<CompareUnitDouble<T>>{
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


	@Override
	public int compareTo(CompareUnitDouble<T> comp) {
		return this.value <  comp.value ? 1 : this.value == comp.value ? 0 : -1;
	}
}
