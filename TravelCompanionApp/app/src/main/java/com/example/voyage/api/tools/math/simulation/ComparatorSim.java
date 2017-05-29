package com.example.voyage.api.tools.math.simulation;

import com.example.voyage.api.tools.math.compare.CompareUnitDouble;
import com.example.voyage.api.tools.math.compare.MathComparator;
import com.example.voyage.api.tools.math.compare.MathUnitComparator;

public class ComparatorSim {

		public static void main(String[] args) {
		double a = 3;
		double b = 5;
		
		System.out.println("3 > 5 : " + MathComparator.getByNameDouble(">").compare(a, b));
		System.out.println("3 > 3 : " + MathComparator.getByNameDouble(">").compare(a, a));
		System.out.println("5 > 3 : " + MathComparator.getByNameDouble(">").compare(b, a));
		System.out.println("\n");
		System.out.println("3 < 5 : " + MathComparator.getByNameDouble("<").compare(a, b));
		System.out.println("3 < 3 : " + MathComparator.getByNameDouble("<").compare(a, a));
		System.out.println("5 < 3 : " + MathComparator.getByNameDouble("<").compare(b, a));
		System.out.println("\n");
		System.out.println("3 >= 5 :" + MathComparator.getByNameDouble(">=").compare(a, b));
		System.out.println("3 >= 3 : " + MathComparator.getByNameDouble(">=").compare(a, a));
		System.out.println("5 >= 3 : " + MathComparator.getByNameDouble(">=").compare(b, a));
		System.out.println("\n");
		System.out.println("3 <= 5 : " + MathComparator.getByNameDouble("<=").compare(a, b));
		System.out.println("3 <= 3 : " + MathComparator.getByNameDouble("<=").compare(a, a));
		System.out.println("5 <= 3 : " + MathComparator.getByNameDouble("<=").compare(b, a));
		
		String  st1 = "bla4";
		String  st2 = "bla8";
		System.out.println(st1.substring(3));
		CompareUnitDouble<String> cud1 = new CompareUnitDouble<String>(Double.parseDouble(st1.substring(3)), st1); 
		CompareUnitDouble<String> cud2 = new CompareUnitDouble<String>(Double.parseDouble(st2.substring(3)), st2); 
		
		System.out.println(cud1.getElement() +" < "+ cud2.getElement() +" : "+ MathUnitComparator.getByNameDouble("<").compare(cud1, cud2));
		System.out.println(cud1.getElement() +" < "+ cud1.getElement() +" : "+ MathUnitComparator.getByNameDouble("<").compare(cud1,cud1));
		System.out.println(cud2.getElement() +" < "+ cud1.getElement() +" : "+ MathUnitComparator.getByNameDouble("<").compare(cud2,cud1));

		}
		
}
