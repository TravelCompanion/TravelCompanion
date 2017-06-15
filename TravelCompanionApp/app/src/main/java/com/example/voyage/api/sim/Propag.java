package com.example.voyage.api.sim;

import java.util.ArrayList;

import com.example.voyage.api.api.data.TheoricDataBase;
import com.example.voyage.api.api.data.TheoricPlace;
import com.example.voyage.api.api.ia.IAManager;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.model.Utilisateur;
import com.example.voyage.api.tools.math.compare.CompareUnitDouble;

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
