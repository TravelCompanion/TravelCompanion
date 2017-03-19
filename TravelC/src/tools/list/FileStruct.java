package tools.list;

import java.util.ArrayList;

import tools.math.CoordinatesDouble;

public class FileStruct<T> {
	private ArrayList<T> list = new ArrayList<T>();

	public void add(T elt) {
		list.add(elt);
	}

	public T getFirst() {
		return isVoid() ? null:list.get(0);
	}

	public void removeFirst() {
		if(!isVoid())list.remove(0);
	}

	public boolean isVoid() {
		return list.size() == 0;
	}

	public T extractFisrt() {
		if(isVoid()) return null;
		T elt = list.get(0);
		list.remove(0);
		return elt;
	}
	
	public String toString(){
		String elt = "";
		for(int i = 0; i < list.size(); i ++)
			elt += list.get(0).toString(); 
		return elt;
	}

	public int size() {
		return list.size();
	}

	public boolean contains(T elt) {
		return list.contains(elt);
	}
}
