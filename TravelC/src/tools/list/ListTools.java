package tools.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tools.math.MathComparator;


public class ListTools {
	public static ArrayList<Double> sortListByNumberDesc(ListTools listTools,ArrayList<Double> list){
		 list.sort(MathComparator.getDouble(MathComparator.SUP_DOUBLE));
		 return list;
	}
	
	public static ArrayList<String> sortListByStringDesc(ListTools listTools,ArrayList<String> list){
		 list.sort(MathComparator.getDouble(MathComparator.SUP_STRING));
		 return list;
	}
	
	public static ArrayList<Double> sortListByNumberAsc(ListTools listTools,ArrayList<Double> list){
		 list.sort(MathComparator.getDouble(MathComparator.INF_DOUBLE));
		 return list;
	}
	
	public static ArrayList<String> sortListByStringAsc(ListTools listTools,ArrayList<String> list){
		 list.sort(MathComparator.getDouble(MathComparator.INF_STRING));
		 return list;
	}
	
	public static <T> void listToTab(List<T> list,T[] tab){
		for(int i = 0; i < list.size(); i++)
			tab[i] = list.get(i);
	}
	
	public static void sortListStringByDouble(ArrayList<String> strings, ArrayList<Double> doubles){
		String[] str =  new String[strings.size()];
		listToTab(strings, str);
		Double[] dou = new Double[doubles.size()];
		listToTab(doubles, dou);
		sortTowTab(str, dou, MathComparator.getDouble(MathComparator.INF_DOUBLE));
		 for(int i = 0; i < str.length; i++){
			 strings.set(i, str[i]);
			 doubles.set(i, dou[i]);
		 }
	}

	private static <T1> void sortTowTabFromDouble(T1[] tab,Double[]tab2,MathComparator comparator)
	{
	    boolean tab_en_ordre = false;
	    int taille = tab.length;
	    while(!tab_en_ordre)
	    {
	        tab_en_ordre = true;
	        for(int index=0 ; index < taille-1 ; index++)
	        {
	            if(tab2[index] < tab2[index+1])
	            {
	            	swapOnTwoTab(tab, tab2, index, index+1);;
	                tab_en_ordre = false;
	            }
	        }
	        taille--;
	    }
	}
	
	private static <T1,T2> void sortTowTab(T1[] tab,T2[]tab2,Comparator<T2> comparator)
	{
	    boolean tab_en_ordre = false;
	    int taille = tab.length;
	    while(!tab_en_ordre)
	    {
	        tab_en_ordre = true;
	        for(int index=0 ; index < taille-1 ; index++)
	        {
	            if(comparator.compare(tab2[index], tab2[index+1]) == 1)
	            {
	            	swapOnTwoTab(tab, tab2, index, index+1);;
	                tab_en_ordre = false;
	            }
	        }
	        taille--;
	    }
	}

	
	private static <T1,T2> void swapOnTwoTab(T1[] t1s, T2[] t2s,int start,int end){
		T1 t12;
		T2 t22;
		int tmp;		
		
		t12 = t1s[end];
		t22 = t2s[end];
		tmp = end;
		
		t1s[start] = t1s[end];
		t2s[start] = t2s[end];
		start = end;
		
		t1s[end] = t12;
		t2s[end] = t22;
		start = tmp;
		
	}
	
}

