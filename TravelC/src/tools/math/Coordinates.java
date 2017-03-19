package tools.math;

import java.util.Arrays;

public class Coordinates {
	private int dim;
	private int[]coords;

	public Coordinates(int dim){
		this.dim = dim;
		coords = new int[dim];
	}
	public Coordinates(int[] coords){
		this.coords = coords;
		this.dim = coords.length;
	}
	public int getX(){
		return coords[0];
	}
	public int getY(){
		return dim > 1 ? coords[1] : 0;
	}
	public int getZ(){
		return dim > 2 ? coords[2] : 0;
	}
	public int getA(){
		return dim > 3 ? coords[3] : 0;
	}
	
	public int getCoordAt(int i){
		return dim > i ? coords[i] : 0;
	}
	
	public void setX(int p){
		coords[0] = p;
	}
	public void setY(int p){
		if(dim > 1)coords[1] = p;
	}
	public void setZ(int p){
		if(dim > 2)coords[2] = p;
	}
	public void setA(int p){
		if(dim > 3)coords[3] = p;
	}
	
	public void setCoordAt(int i, int p){
		if(dim > i)coords[i] = p;
	}
	
	public static boolean eq(Coordinates a, Coordinates b){
		int k = Math.max(a.dim, b.dim);
		for(int i = 0; i< k;i++)
			if(a.coords[i] != b.coords[i])return false;
		return true;
	}
	
	public static boolean sup(Coordinates a, Coordinates b){
		return distanceFromOrigine(a) > distanceFromOrigine(b);
	}
	
	public static boolean inf(Coordinates a, Coordinates b){
		return distanceFromOrigine(a) < distanceFromOrigine(b);
	}
	
	public static int distanceFromOrigine(Coordinates c){
		int sum = 0;
		for(int i = 0; i < c.dim; i ++)
			sum += c.coords[i] < 0 ? -c.coords[i] : c.coords[i];
			return sum;
	}
	
	public static int distanceAtoB(Coordinates a, Coordinates b){
		int k = Math.max(a.dim, b.dim);
		int sum = 0;
		int v = 0;
		for(int i = 0; i < k; i ++)
			v = a.coords[i] - b.coords[i];
		sum += v < 0 ? -v : v;
			return sum;
	}
	
	
	public String toString() {
		return "Coordinates [dim=" + dim + ", coords=" + Arrays.toString(coords)
				+ "]";
	}
	
	
}