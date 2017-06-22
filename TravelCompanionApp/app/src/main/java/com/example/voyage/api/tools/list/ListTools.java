package com.example.voyage.api.tools.list;

import java.util.ArrayList;
import java.util.List;

import com.example.voyage.api.tools.math.compare.CompareUnitDouble;
import com.example.voyage.api.tools.math.compare.MathComparator;
import com.example.voyage.api.tools.math.compare.MathUnitComparator;


public class ListTools {
	public static ArrayList<Double> sortListByNumberDesc(ArrayList<Double> list){
		//noinspection Since15
		list.sort(MathComparator.getByNameDouble("<"));
		 return list;
	}
	
	public static ArrayList<String> sortListByStringDesc(ArrayList<String> list){
		//noinspection Since15
		list.sort(MathComparator.getByNameString("<"));
		 return list;
	}
	
	public static ArrayList<Double> sortListByNumberAsc(ArrayList<Double> list){
		//noinspection Since15
		list.sort(MathComparator.getByNameDouble(">"));
		 return list;
	}
	
	public static ArrayList<String> sortListByStringAsc(ArrayList<String> list){
		//noinspection Since15
		list.sort(MathComparator.getByNameString(">"));
		 return list;
	}
	
	public static <T> void listToTab(List<T> list,T[] tab){
		for(int i = 0; i < list.size(); i++)
			tab[i] = list.get(i);
	}
	
	public static <T> ArrayList<T> sortCompareUnitDoubleListAsc(ArrayList<CompareUnitDouble<T>> list){
		ArrayList<T> sortList = new ArrayList<T>();
        //noinspection Since15
        list.sort(MathUnitComparator.getByNameDouble("<"));
		for(CompareUnitDouble<T> elt : list)
			sortList.add(elt.getElement());
		return sortList;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> ArrayList<T>[] createTabList(int sizeTab){
		Object tab = new Object[sizeTab];
		ArrayList<T>[] tabOfList = (ArrayList<T>[])tab;
		for(int x = 0;x < sizeTab; x++)
			tabOfList[x] = new ArrayList<T>();
		return tabOfList;
		
	}
}