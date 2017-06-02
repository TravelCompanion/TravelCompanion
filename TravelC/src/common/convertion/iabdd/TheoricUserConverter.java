package common.convertion.iabdd;

import java.util.ArrayList;

import api.data.TheoricMainUser;
import api.data.TheoricUser;
import common.convertion.generic.UserConverter;
import common.type.TypeConfiguration;
import model.Utilisateur;
import tools.math.Matrix;
import tools.parse.StringParser;

public class TheoricUserConverter implements UserConverter<TheoricUser> {

	public TheoricUser fromDatabase(Utilisateur data) {
		TheoricUser theoricUser = new TheoricUser(data.getUserName());
		return theoricUser;
	}

	public TheoricMainUser fromDatabaseToMainUser(Utilisateur data) {
		
		Matrix pref = new Matrix(1, TypeConfiguration.number);
		ArrayList<String> lines = StringParser.sliceLine(data.getPreferences(), ',');
		for (int i = 0; i < TypeConfiguration.number; i++)
			pref.setValue(0, i, Double.parseDouble(lines.get(i)));
		TheoricMainUser theoricMainUser = new TheoricMainUser(data.getUserName(), pref);
		return theoricMainUser;
	}

	public Utilisateur toDataBase(TheoricUser data) {
		Matrix tmp = data.getPreferences();
		String pref = "" + tmp.getValue(0, 0);
		for (int i = 1; i < tmp.sizeY; i++)
			pref += tmp.getValue(0, i) + ",";
		return new Utilisateur("", data.getUserName(), pref);
	}
	

}
