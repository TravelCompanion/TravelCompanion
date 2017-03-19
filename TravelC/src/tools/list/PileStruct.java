package tools.list;

import java.util.ArrayList;

public class PileStruct<T> {
	public ArrayList<T> list = new ArrayList<T>();

	public void add(T elt) {
		list.add(elt);
	}

	public T getLast() {
		return isVoid() ? null:list.get(list.size() - 1);
	}

	public boolean isVoid() {
		return list.size() == 0;
	}

	public void removeLast() {
		if(!isVoid())list.remove(list.size() - 1);
	}

	public T extractLast() {
		if(isVoid()) return null;
		T elt = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return elt;
	}
	public String toString(){
		String elt = "";
		for(int i = 0; i < list.size(); i ++)
			elt += list.get(0).toString(); 
		return elt;
	}
}
