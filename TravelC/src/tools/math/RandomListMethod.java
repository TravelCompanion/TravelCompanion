package tools.math;

import java.util.ArrayList;
import java.util.Random;

public class RandomListMethod {
	public static <T> T extractRandElt(ArrayList<T> list, Random r) {
		int i = RandomInt.generate(r, list.size() - 1);
		// System.out.println(i);
		T elt = list.get(i);
		list.remove(i);
		return elt;
	}

	public static <T> T pickInList(ArrayList<T> list, Random r) {
		return (list.get(RandomInt.generate(r, list.size() - 1)));
	}

}
