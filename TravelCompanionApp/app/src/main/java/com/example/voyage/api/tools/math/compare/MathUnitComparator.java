package com.example.voyage.api.tools.math.compare;

import java.util.Comparator;

public class MathUnitComparator<T1> {

	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> getByNameDouble(String symb) {
		switch (symb) {
		case "<":
			return (Comparator<T>) MathUnitComparator.InferiorUnitDouble.get();
		case ">":
			return (Comparator<T>) MathUnitComparator.SuperiorUnitDouble.get();
		case "<=":
			return (Comparator<T>) MathUnitComparator.InferiorEqualUnitDouble.get();
		case ">=":
			return (Comparator<T>) MathUnitComparator.SuperiorEqualUnitDouble.get();
		default:
			return null;
		}
	}

	private static class InferiorUnitDouble<T1> implements Comparator<CompareUnitDouble<T1>> {
		public static <T1> InferiorUnitDouble<T1> get() {
			return new InferiorUnitDouble<T1>();
		}

		public int compare(CompareUnitDouble<T1> o1, CompareUnitDouble<T1> o2) {
			return o1.getValue() < o2.getValue() ? 1 : -1;
		}

	}

	private static class SuperiorUnitDouble<T1> implements Comparator<CompareUnitDouble<T1>> {
		public static <T1> SuperiorUnitDouble<T1> get() {
			return new SuperiorUnitDouble<T1>();
		}

		public int compare(CompareUnitDouble<T1> o1, CompareUnitDouble<T1> o2) {
			return o1.getValue() > o2.getValue() ? 1 : -1;
		}

	}

	private static class InferiorEqualUnitDouble<T1> implements Comparator<CompareUnitDouble<T1>> {
		public static <T1> InferiorEqualUnitDouble<T1> get() {
			return new InferiorEqualUnitDouble<T1>();
		}

		public int compare(CompareUnitDouble<T1> o1, CompareUnitDouble<T1> o2) {
			return o1.getValue() < o2.getValue() ? 1 : o1.getValue() == o2.getValue() ? 0 : -1;
		}

	}

	private static class SuperiorEqualUnitDouble<T1> implements Comparator<CompareUnitDouble<T1>> {
		public static <T1> SuperiorEqualUnitDouble<T1> get() {
			return new SuperiorEqualUnitDouble<T1>();
		}

		public int compare(CompareUnitDouble<T1> o1, CompareUnitDouble<T1> o2) {
			return o1.getValue() > o2.getValue() ? 1 : o1.getValue() == o2.getValue() ? 0 : -1;
		}

	}
	
	@SuppressWarnings("unchecked")
	public static <T> Comparator<T> getByNameString(String symb) {
		switch (symb) {
		case "<":
			return (Comparator<T>) MathUnitComparator.InferiorUnitString.get();
		case ">":
			return (Comparator<T>) MathUnitComparator.SuperiorUnitString.get();
		case "<=":
			return (Comparator<T>) MathUnitComparator.InferiorEqualsUnitString.get();
		case ">=":
			return (Comparator<T>) MathUnitComparator.SuperiorEqualsUnitString.get();
		default:
			return null;
		}
	}

	private static class InferiorUnitString implements Comparator<String> {
		public static InferiorUnitString get() {
			return new InferiorUnitString();
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

	private static class InferiorEqualsUnitString implements Comparator<String> {
		public static InferiorEqualsUnitString get() {
			return new InferiorEqualsUnitString();
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

	private static class SuperiorUnitString implements Comparator<String> {
		public static SuperiorUnitString get() {
			return new SuperiorUnitString();
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

	private static class SuperiorEqualsUnitString implements Comparator<String> {
		public static SuperiorEqualsUnitString get() {
			return new SuperiorEqualsUnitString();
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

}
