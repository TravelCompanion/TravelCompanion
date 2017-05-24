package tools.math.compare;

public class CompareUnitString<T> {
	private String value;
	private T element;
	
	public CompareUnitString(String value, T element) {
		this.value = value;
		this.element = element;
	}
	
	public String getValue() {
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
