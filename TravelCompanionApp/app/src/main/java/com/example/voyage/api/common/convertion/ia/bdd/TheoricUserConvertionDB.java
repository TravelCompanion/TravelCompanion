package com.example.voyage.api.common.convertion.ia.bdd;

import java.util.ArrayList;

import com.example.voyage.api.api.data.TheoricMainUser;
import com.example.voyage.api.api.data.TheoricUser;
import com.example.voyage.api.common.convertion.ia.TheoricUserConverter;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.model.Utilisateur;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.parse.StringParser;

public class TheoricUserConvertionDB implements TheoricUserConverter<Utilisateur> {

	public TheoricUser convertFrom(Utilisateur data) {
		Matrix pref = new Matrix(1, TypeConfiguration.number);
		ArrayList<String> lines = StringParser.sliceLine(data.getPreferences(), ',');
		for (int i = 0; i < TypeConfiguration.number; i++)
			pref.setValue(0, i, Double.parseDouble(lines.get(i)));
		TheoricUser theoricUser = new TheoricUser(data.getId_user(),data.getUserName(),pref);
		return theoricUser;
	}

	public TheoricMainUser convertFromToMainUser(Utilisateur data) {
		
		Matrix pref = new Matrix(1, TypeConfiguration.number);
		ArrayList<String> lines = StringParser.sliceLine(data.getPreferences(), ',');
		for (int i = 0; i < TypeConfiguration.number; i++)
			pref.setValue(0, i, Double.parseDouble(lines.get(i)));
		TheoricMainUser theoricMainUser = new TheoricMainUser(data.getId_user(),data.getUserName(), pref);
		return theoricMainUser;
	}

	public Utilisateur convertTo(TheoricUser data) {
		Matrix tmp = data.getPreferences();
		String pref = "" + tmp.getValue(0, 0);
		for (int i = 1; i < tmp.sizeY; i++)
			pref += tmp.getValue(0, i) + ",";
		return new Utilisateur(data.getId(),"",data.getUserName(),pref);
	}
	

}
