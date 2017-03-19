package tools.math;

import java.util.Arrays;

public class CoordinatesDouble {
	private int dim;
	private double[]coords;

	public CoordinatesDouble(int dim){
		this.dim = dim;
		coords = new double[dim];
	}
	
	public CoordinatesDouble(double tab[]){
		this.dim = tab.length;
		coords = tab;
	}
	
	public double getX(){
		return coords[0];
	}
	public double getY(){
		return dim > 1 ? coords[1] : 0;
	}
	public double getZ(){
		return dim > 2 ? coords[2] : 0;
	}
	public double getA(){
		return dim > 3 ? coords[3] : 0;
	}
	
	public double getCoordAt(int i){
		return dim > i ? coords[i] : 0;
	}
	
	public static boolean eq(CoordinatesDouble a, CoordinatesDouble b){
		double k = Math.max(a.dim, b.dim);
		for(int i = 0; i< k;i++)
			if(a.coords[i] != b.coords[i])return false;
		return true;
	}
	
	public static boolean sup(CoordinatesDouble a, CoordinatesDouble b){
		return distanceFromOrigine(a) > distanceFromOrigine(b);
	}
	
	public static boolean inf(CoordinatesDouble a, CoordinatesDouble b){
		return distanceFromOrigine(a) < distanceFromOrigine(b);
	}
	
	public static double distanceFromOrigine(CoordinatesDouble c){
		return Math.pow(sqrDistanceFromOrigine(c), 0.5);
	}
	
	public static double sqrDistanceAtoB(CoordinatesDouble a, CoordinatesDouble b){
		int k = Math.max(a.dim, b.dim);
		double sum = 0;
		for(int i = 0; i<k; i++)
			sum += Math.pow(a.coords[i]-b.coords[i],2);
		return sum;
	}
	
	public static double sqrDistanceAtoB(CoordinatesDouble a, CoordinatesDouble b,int dim){
		double sum = 0;
		for(int i = 0; i<dim; i++)
			sum += Math.pow(a.coords[i]-b.coords[i],2);
		return sum;
	}
	
	public static double sqrDistanceFromOrigine(CoordinatesDouble a){
		double sum = 0;
		for(int i = 0; i<a.dim; i++)
			sum += Math.pow(a.coords[i],2);
		return sum;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coords);
		result = prime * result + dim;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinatesDouble other = (CoordinatesDouble) obj;
		if (!Arrays.equals(coords, other.coords))
			return false;
		if (dim != other.dim)
			return false;
		return true;
	}

	public static double distanceAtoB(CoordinatesDouble a, CoordinatesDouble b){
			return Math.pow(sqrDistanceAtoB(a, b), 0.5);
	}

	public String toString() {
		return "CoordinatesDouble [coords=" + Arrays.toString(coords) + "]";
	}
	
}
