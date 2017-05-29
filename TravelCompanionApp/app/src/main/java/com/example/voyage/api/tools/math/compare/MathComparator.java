package com.example.voyage.api.tools.math.compare;

import java.util.Comparator;

public class MathComparator {

	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> getByNameDouble(String symb) {
		switch (symb) {
		case "<":
			return (Comparator<T>) InferiorDouble.get();
		case ">":
			return (Comparator<T>) SuperiorDouble.get();
		case "<=":
			return (Comparator<T>) InferiorEqualsDouble.get();
		case ">=":
			return (Comparator<T>) SuperiorEqualsDouble.get();
		default:
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> getByNameString(String symb) {
		
		switch (symb) {
		case "<":
			return (Comparator<T>) InferiorString.get();
		case ">":
			return (Comparator<T>) SuperiorString.get();
		case "<=":
			return (Comparator<T>) InferiorEqualsString.get();
		case ">=":
			return (Comparator<T>) SuperiorEqualsString.get();
		default:
			return null;
		}
	}
	

	
	
	
	
	private static class InferiorString implements Comparator<String> {
		public static InferiorString get() {
			return new InferiorString();
		}

		public int compare(String o1, String o2) {
			int index = 0, l1 = o1.length(), l2 = o2.length();
			while (index < l1 && index < l2) {
				if (o1.charAt(index) < o2.charAt(index))
					return 1;
				if (o1.charAt(index) > o2.charAt(index))
					return -1;
			}
			return l1 < l2 ? 1 : l1 == l2 ? 0 : -1;
		}
	}

	private static class InferiorEqualsString implements Comparator<String> {
		public static InferiorEqualsString get() {
			return new InferiorEqualsString();
		}

		public int compare(String o1, String o2) {
			int index = 0, l1 = o1.length(), l2 = o2.length();
			while (index < l1 && index < l2) {
				if (o1.charAt(index) <= o2.charAt(index))
					return 1;
				if (o1.charAt(index) > o2.charAt(index))
					return -1;
			}
			return l1 <= l2 ? 1 : -1;
		}
	}

	private static class SuperiorString implements Comparator<String> {
		public static SuperiorString get() {
			return new SuperiorString();
		}

		public int compare(String o1, String o2) {
			int index = 0, l1 = o1.length(), l2 = o2.length();
			while (index < l1 && index < l2) {
				if (o1.charAt(index) < o2.charAt(index))
					return -1;
				if (o1.charAt(index) > o2.charAt(index))
					return 1;
			}
			return l1 < l2 ? -1 : l1 == l2 ? 0 : 1;
		}
	}

	private static class SuperiorEqualsString implements Comparator<String> {
		public static SuperiorEqualsString get() {
			return new SuperiorEqualsString();
		}

		public int compare(String o1, String o2) {
			int index = 0, l1 = o1.length(), l2 = o2.length();
			while (index < l1 && index < l2) {
				if (o1.charAt(index) < o2.charAt(index))
					return -1;
				if (o1.charAt(index) >= o2.charAt(index))
					return 1;
			}
			return l1 < l2 ? -1 : 1;
		}
	}
	
	private static class InferiorDouble implements Comparator<Double> {
		public static InferiorDouble get() {
			return new InferiorDouble();
		}

		public int compare(Double o1, Double o2) {
			return o1 < o2 ? 1 : o1 == o2 ? 0 : -1;
		}

	}

	private static class InferiorEqualsDouble implements Comparator<Double> {
		public static InferiorDouble get() {
			return new InferiorDouble();
		}

		public int compare(Double o1, Double o2) {
			return o1 <= o2 ? 1 : -1;
		}

	}

	private static class SuperiorDouble implements Comparator<Double> {
		public static SuperiorDouble get() {
			return new SuperiorDouble();
		}

		public int compare(Double o1, Double o2) {
			return o1 > o2 ? 1 : o1 == o2 ? 0 : -1;
		}
	}

	private static class SuperiorEqualsDouble implements Comparator<Double> {
		public static SuperiorEqualsDouble get() {
			return new SuperiorEqualsDouble();
		}

		public int compare(Double o1, Double o2) {
			return o1 >= o2 ? 1 : -1;
		}
	}

}
