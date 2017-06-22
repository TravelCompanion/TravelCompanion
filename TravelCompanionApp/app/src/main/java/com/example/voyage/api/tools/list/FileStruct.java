package com.example.voyage.api.tools.list;

import java.util.ArrayList;

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

	public boolean contains(T elt){
		return list.contains(elt);
	}
	
	public int size() {
		return list.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileStruct other = (FileStruct) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		return true;
	}
	
}
