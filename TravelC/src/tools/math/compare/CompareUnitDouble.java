package tools.math.compare;

public class CompareUnitDouble<T> implements Comparable<CompareUnitDouble<T>>{
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

	public String toString() {
		return "CompareUnitDouble [value=" + value + ", element=" + element + "]";
	}

	public int compareTo(CompareUnitDouble<T> o) {
		return this.value < o.value ? 1 : this.value == o.value ? 0 :-1;
	}
	
	
}
