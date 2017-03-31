package tools.math;

import java.util.Comparator;

public enum MathComparator {
	INF_DOUBLE(0), SUP_DOUBLE(1), INFEQ_DOUBLE(2), SUPEQ_DOUBLE(3), INF_STRING(4), SUP_STRING(5), INFEQ_STRING(
			6), SUPEQ_STRING(7);

	private int id = 0;

	private MathComparator(int id) {
		this.id = id;
	}

	private static int getId(MathComparator comparator) {
		return comparator.id;
	}

	public static <T> Comparator<T> getByNameDouble(String symb) {
		switch (symb) {
		case "<":
			return getDouble(INF_DOUBLE);
		case ">":
			return getDouble(SUP_DOUBLE);
		case "<=":
			return getDouble(INFEQ_DOUBLE);
		case ">=":
			return getDouble(SUPEQ_DOUBLE);
		default:
			return null;
		}
	}

	public static <T> Comparator<T> getByNameString(String symb) {
		switch (symb) {
		case "<":
			return getDouble(INF_STRING);
		case ">":
			return getDouble(SUP_STRING);
		case "<=":
			return getDouble(INFEQ_STRING);
		case ">=":
			return getDouble(SUPEQ_STRING);
		default:
			return null;
		}
	}

	@SuppressWarnings({ "unchecked" })
	public static <T> Comparator<T> getDouble(MathComparator comparator) {
		Comparator<T> comp = null;
		switch (getId(comparator)) {
		case 0:
			comp = (Comparator<T>) InferiorDouble.get();
		case 1:
			comp = (Comparator<T>) SuperiorDouble.get();
		case 2:
			comp = (Comparator<T>) InferiorEqualsDouble.get();
		case 3:
			comp = (Comparator<T>) SuperiorEqualsDouble.get();
		case 4:
			comp = (Comparator<T>) InferiorString.get();
		case 5:
			comp = (Comparator<T>) SuperiorString.get();
		case 6:
			comp = (Comparator<T>) InferiorEqualsString.get();
		case 7:
			comp = (Comparator<T>) SuperiorEqualsString.get();

		}
		return comp;
	}

	private static class InferiorString implements Comparator<String> {
		public static SuperiorString get() {
			return new SuperiorString();
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
		public static SuperiorEqualsString get() {
			return new SuperiorEqualsString();
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
