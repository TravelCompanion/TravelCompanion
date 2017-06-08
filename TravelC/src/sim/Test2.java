package sim;

import java.util.ArrayList;

import model.Monument;
import model.Utilisateur;
import persistence.PersistenceData;
import tools.math.random.RandomInt;

public class Test2 {
	public static void main(String[] args){
	/*	PersistenceData p = new PersistenceData();
		
		ArrayList<Monument> monuments = new ArrayList<Monument>();
		monuments = p.persisteMonument(49.036407,2.0760956);
		Utilisateur user = p.User(1);
		
		Propag.progation(user, monuments);*/
		System.out.println(RandomInt.generate(340,1));
	}
}
