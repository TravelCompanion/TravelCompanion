package persistence;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.data.NoPlaceFoundException;
import common.data.NoUserFoundException;
import common.data.YouHaveNoFriendsExeption;
import model.Message;
import model.Monument;
import model.Musee;
import model.Utilisateur;
import tools.parse.StringParser;

public class PersistenceData {

	private Parametre a;
	public DataBase db;
	private List<Musee> musees = new ArrayList<Musee>();
	private List<Utilisateur> users = new ArrayList<Utilisateur>();
	private List<Message> messages = new ArrayList<Message>();
	private List<Monument> monuments = new ArrayList<Monument>();


	public PersistenceData() {
		ArrayList<String> lines= StringParser.readData("./data/databaseconfig.txt");
		this.setA(new Parametre(lines.get(0),lines.get(1),lines.get(2)));
		db = new DataBase(Parametre.Host, Parametre.username, Parametre.password, Parametre.IPHOST, Parametre.Port);
	}
	
	public PersistenceData(String userName, String password, String database) {

		this.setA(new Parametre(userName, password, database));
		db = new DataBase(Parametre.Host, Parametre.username, Parametre.password, Parametre.IPHOST, Parametre.Port);
	}
	
	public boolean VerifierUtilisateur(String userName, String email, String password)
			throws IOException, SQLException {

		boolean test = false;
		String emailData = null;

		ResultSet Res;

		Res = db.querySelectAll("utilisateur", "email='" + email + "'");

		while (Res.next()) {
			emailData = Res.getString("email");

		}

		if (emailData != null) {
			return false;
		} else {
			test = true;
			persisteUtilisateur(userName, email, password);
		}
		return test;
	}

	public int VerifierUtilisateur(String email, String password) throws IOException, SQLException {

		int id = -1;

		ResultSet Res;

		Res = db.querySelectAll("utilisateur", "email='" + email + "' and password='" + password + "'");

		while (Res.next()) {
			id = Res.getInt(1);
		}

		return id;
	}

	public void persisteUtilisateur(String userName, String email, String password)
			throws NumberFormatException, SQLException {

		ResultSet Res;
		int s = 0;

		Res = db.executionQuery("select count(*) from utilisateur");

		while (Res.next()) {

			s = Integer.parseInt(Res.getString("count(*)"));
			s++;
		}
		String tab[] = { "" + s, userName, email, password };

		db.queryInsert("utilisateur", tab);

	}

	public List<Musee> persisteMusee(Double lat, Double lng) throws SQLException {

		ResultSet Res;

		Res = db.executionQuery("SELECT *,haversine_distance(latitude, longitude," + lat + "," + lng
				+ ") FROM musee WHERE haversine_distance(latitude, longitude," + lat + "," + lng
				+ ") < 20 order by haversine_distance(latitude, longitude," + lat + "," + lng + ") limit 3");

		while (Res.next()) {
			Musee m = new Musee(Res.getString(2), Res.getString(3), Res.getString(4), Res.getString(5), Res.getInt(6),
					Res.getString(7), Res.getString(8), Res.getString(9), Res.getString(10), Res.getString(11),
					Res.getString(12), Res.getDouble(13), Res.getDouble(14), Res.getDouble(15));

			musees.add(m);
		}

		return musees;

	}

	public List<Utilisateur> persisteAmis(int id) throws SQLException, YouHaveNoFriendsExeption {

		ResultSet Res;
		users.clear();
		Res = db.executionQuery(
				"select distinct userName,email from utilisateur,est_ami where utilisateur.id_user=est_ami.id_user_Utilisateur and est_ami.id_user='"
						+ id + "'");
		if(Res == null)throw new YouHaveNoFriendsExeption();
		else{
		while (Res.next()) {
			Utilisateur u = new Utilisateur(Res.getString(1), Res.getString(2));

			users.add(u);
		}
		}
		return users;

	}

	public Utilisateur loadUser(int id) throws SQLException, NoUserFoundException {

		ResultSet Res;
		Res = db.executionQuery("select * from utilisateur where id_user=" + id);
		Utilisateur u = null;

		if (Res == null)
			throw new NoUserFoundException();
		else {
			while (Res.next()) {
				u = new Utilisateur(Res.getString(3), Res.getString(2), Res.getString(5));

			}
		}
		return u;

	}

	public List<Message> message(int id) throws SQLException {

		ResultSet Res;
		messages.clear();
		Res = db.executionQuery(
				"select date_message,utilisateur.userName,corps from est_ami,message,utilisateur where est_ami.id_user="
						+ id
						+ " and est_ami.id_message=message.id_message and est_ami.id_user_Utilisateur=utilisateur.id_user group by  utilisateur.userName order by date_message desc");
		Message m = null;

		while (Res.next()) {

			m = new Message(Res.getString(1), Res.getString(2), Res.getString(3));

			messages.add(m);

		}

		return messages;

	}

	public List<Monument> loadMonument(Double latitude, Double longitude, Double range)
			throws SQLException, NoPlaceFoundException {

		ResultSet res;
		monuments.clear();
		res = db.executionQuery("SELECT *,haversine_distance(latitude, longitude," + latitude + "," + longitude
				+ ")FROM monument WHERE haversine_distance(latitude, longitude," + latitude + "," + longitude + ") < " + range
				+ " ORDER BY haversine_distance(latitude, longitude," + latitude + "," + longitude + ") asc");
		if (res == null)
			throw new NoPlaceFoundException();
		else {
			while (res.next()) {
				Monument m = new Monument(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),
						res.getInt(5), res.getDouble(6), res.getDouble(7), res.getString(8), res.getString(9),
						res.getDouble(10));

				monuments.add(m);
			}
		}
		return monuments;
	}

	public List<Monument> loadMonumentSite(Double latitude, Double longitude) throws SQLException {

		ResultSet Res;
		monuments.clear();
		Res = db.executionQuery("SELECT *,haversine_distance(latitude, longitude," + latitude + "," + longitude
				+ ")FROM monument WHERE haversine_distance(latitude, longitude," + latitude + "," + longitude
				+ ") < 20 ORDER BY haversine_distance(latitude, longitude," + latitude + "," + longitude + ") asc limit 3");

		while (Res.next()) {
			Monument m = new Monument(Res.getInt(1), Res.getString(2), Res.getString(3), Res.getString(4),
					Res.getInt(5), Res.getDouble(6), Res.getDouble(7), Res.getString(8), Res.getString(9),
					Res.getDouble(10));

			monuments.add(m);
		}

		return monuments;

	}

	public Parametre getA() {
		return a;
	}

	public void setA(Parametre a) {
		this.a = a;
	}

	public void updateUserPref(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		System.out.println(utilisateur);
		System.out.println("user up-to-date");
	}

}
