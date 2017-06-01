package api.data;

import java.util.ArrayList;


import common.type.TypeConfiguration;
import model.Utilisateur;
import tools.math.Matrix;
import tools.parse.StringParser;

public class TheoricMainUser extends TheoricUser{
	public TheoricMainUser() {
		// TODO Auto-generated constructor stub
	} 
	public TheoricMainUser(String userName, Matrix pref) {
		super(userName,pref);
	}
	public TheoricMainUser fromDatabaselUser(Utilisateur utilisateur){
		
		Matrix pref = new Matrix(1,TypeConfiguration.number);
		ArrayList<String> lines = StringParser.sliceLine(utilisateur.getPreferences(), ',');
		for(int i = 0; i < TypeConfiguration.number; i++)
			pref.setValue(0,i,Double.parseDouble(lines.get(i)));
		TheoricMainUser theoricMainUser = new TheoricMainUser(utilisateur.getUserName(),pref);
		return theoricMainUser;
	}
	public void updatePref(Matrix weights) {
		this.preferences = weights;
	}

}
