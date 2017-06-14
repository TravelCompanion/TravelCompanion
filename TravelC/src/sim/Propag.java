package sim;

import java.util.ArrayList;

import api.data.TheoricDataBase;
import api.data.TheoricPlace;
import api.ia.IAManager;
import model.Monument;
import model.Utilisateur;
import tools.math.compare.CompareUnitDouble;

public class Propag {

	public static ArrayList<Monument> progation(Utilisateur user, ArrayList<Monument> monuments) {
		TheoricDataBase.getDataBase();
		// initialisation de la classe TypeConfiguration
		TheoricDataBase.newMainUser(user);
		TheoricDataBase.newClosePlaces(monuments);
		ArrayList<CompareUnitDouble<TheoricPlace>> list = IAManager.choosePlaces();
		ArrayList<Monument> monumentsR = new ArrayList<Monument>();
		for(CompareUnitDouble<TheoricPlace> placeData : list)
			monumentsR.add(TheoricDataBase.MONUMENT_CONVERTER.convertTo(placeData.getElement()));
		return monumentsR;
	}
}
