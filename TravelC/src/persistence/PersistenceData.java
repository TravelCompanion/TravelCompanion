package persistence;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Message;
import model.Monument;
import model.Musee;
import model.Utilisateur;
import persistence.exception.NoPlaceFoundException;
import persistence.exception.NoSuchUserExeption;
import persistence.exception.NoUserFoundException;
import persistence.exception.YouHaveNoFriendsExeption;
import tools.math.CoordinatesDouble;

public class PersistenceData {

	private Parametre a;

	public DataBase db;

	public PersistenceData() {
	}

	public PersistenceData(String userName, String password, String database) {

		this.setA(new Parametre(userName, password, database));
		db = new DataBase(Parametre.Host, Parametre.username, Parametre.password, Parametre.IPHOST, Parametre.Port);
	}

	private List<Musee> musees = new ArrayList<Musee>();
	private List<Utilisateur> users = new ArrayList<Utilisateur>();
	private List<Utilisateur> usersSearch = new ArrayList<Utilisateur>();
	private List<Message> messages = new ArrayList<Message>();
	private List<Monument> monuments = new ArrayList<Monument>();

	public boolean VerifierUtilisateur(String userName, String email, String password, String preferences)
			throws IOException, SQLException, NoSuchUserExeption {

		boolean test = false;
		String emailData = null;

		ResultSet Res;

		Res = db.querySelectAll("utilisateur", "email='" + email + "'");
		if (Res == null)
			throw new NoSuchUserExeption();
		else {
			while (Res.next()) {
				emailData = Res.getString("email");

			}

			if (emailData != null) {

				return false;
			} else {
				test = true;
				System.out.println(emailData);

				persisteUtilisateur(userName, email, password, preferences);
			}
		}
		return test;
	}

	public int VerifierUtilisateur(String email, String password) throws IOException, SQLException, NoSuchUserExeption {

		int id = -1;

		ResultSet Res;

		Res = db.querySelectAll("utilisateur", "email='" + email + "' and password='" + password + "'");
		if (Res == null)
			throw new NoSuchUserExeption();
		else {
			while (Res.next()) {
				id = Res.getInt(1);
			}
		}
		return id;
	}

	public void persisteUtilisateur(String userName, String email, String password, String preferences)
			throws NumberFormatException, SQLException {

		ResultSet Res;
		int s = 0;

		Res = db.executionQuery("select count(*) from utilisateur");
		while (Res.next()) {

			s = Integer.parseInt(Res.getString("count(*)"));
			s++;
		}
		String tab[] = { "" + s, userName, email, password, preferences };

		db.queryInsert("utilisateur", tab);

	}

	public List<Musee> persisteMusee(Double lat, Double lng) throws SQLException, NoPlaceFoundException {

		ResultSet Res;

		Res = db.executionQuery("SELECT *,haversine_distance(latitude, longitude," + lat + "," + lng
				+ ") FROM musee WHERE haversine_distance(latitude, longitude," + lat + "," + lng
				+ ") < 20 order by haversine_distance(latitude, longitude," + lat + "," + lng + ") limit 3");
		if (Res == null)
			throw new NoPlaceFoundException();
		else {
			while (Res.next()) {
				Musee m = new Musee(Res.getString(2), Res.getString(3), Res.getString(4), Res.getString(5),
						Res.getInt(6), Res.getString(7), Res.getString(8), Res.getString(9), Res.getString(10),
						Res.getString(11), Res.getString(12), Res.getDouble(13), Res.getDouble(14), Res.getDouble(15));

				musees.add(m);
			}
		}
		return musees;

	}

	public List<Utilisateur> persisteAmis(int id) throws SQLException, YouHaveNoFriendsExeption {

		ResultSet Res;
		users.clear();
		Res = db.executionQuery(
				"select distinct est_ami.id_user_Utilisateur,userName,email,preferences from utilisateur,est_ami where utilisateur.id_user=est_ami.id_user_Utilisateur and est_ami.id_user='"
						+ id + "'");
		if (Res == null)
			throw new YouHaveNoFriendsExeption();
		else {
			while (Res.next()) {
				Utilisateur u = new Utilisateur(Res.getInt(1), Res.getString(3), Res.getString(2), Res.getString(4));

				users.add(u);
			}
		}
		return users;

	}

	public Utilisateur User(int id) throws SQLException, NoUserFoundException {

		ResultSet Res;
		Res = db.executionQuery("select * from utilisateur where id_user=" + id);
		Utilisateur u = null;
		if (Res == null)
			throw new NoUserFoundException();
		else {
			while (Res.next()) {
				u = new Utilisateur(Res.getInt(1), Res.getString(3), Res.getString(2), Res.getString(5));

			}
		}
		return u;

	}

	public List<Message> message(int id) throws SQLException {

		ResultSet Res;
		messages.clear();
		Res = db.executionQuery(
				"select utilisateur.id_user,date_message,utilisateur.userName,corps from est_ami,message,utilisateur where est_ami.id_user="
						+ id
						+ " and est_ami.id_message=message.id_message and est_ami.id_user_Utilisateur=utilisateur.id_user group by  utilisateur.userName order by date_message desc");
		Message m = null;
		while (Res.next()) {

			m = new Message(Res.getInt(1), Res.getString(2), Res.getString(3), Res.getString(4));

			messages.add(m);

		}

		return messages;

	}

	public List<Monument> persisteMonument(Double lat, Double lng) throws SQLException, NoPlaceFoundException {

		ResultSet Res;
		monuments.clear();
		Res = db.executionQuery("SELECT *,haversine_distance(latitude, longitude," + lat + "," + lng
				+ ")FROM monument WHERE haversine_distance(latitude, longitude," + lat + "," + lng
				+ ") < 20 ORDER BY haversine_distance(latitude, longitude," + lat + "," + lng + ") asc");
		if (Res == null)
			throw new NoPlaceFoundException();
		else {
			while (Res.next()) {
				Monument m = new Monument(Res.getInt(1), Res.getString(2), Res.getString(3), Res.getString(4),
						Res.getInt(5), Res.getDouble(6), Res.getDouble(7), Res.getString(8), Res.getString(9),
						Res.getDouble(10));

				monuments.add(m);
			}
		}
		return monuments;

	}

	public List<Monument> persisteMonumentSite(Double lat, Double lng) throws SQLException, NoPlaceFoundException {

		ResultSet Res;
		monuments.clear();
		Res = db.executionQuery("SELECT *,haversine_distance(latitude, longitude," + lat + "," + lng
				+ ")FROM monument WHERE haversine_distance(latitude, longitude," + lat + "," + lng
				+ ") < 20 ORDER BY haversine_distance(latitude, longitude," + lat + "," + lng + ") asc limit 3");
		if (Res == null)
			throw new NoPlaceFoundException();
		else {
			while (Res.next()) {
				Monument m = new Monument(Res.getInt(1), Res.getString(2), Res.getString(3), Res.getString(4),
						Res.getInt(5), Res.getDouble(6), Res.getDouble(7), Res.getString(8), Res.getString(9),
						Res.getDouble(10));

				monuments.add(m);
			}
		}
		return monuments;

	}

	public Parametre getA() {
		return a;
	}

	public void setA(Parametre a) {
		this.a = a;
	}

	public Monument profilMonument() throws SQLException {

		ResultSet Res;
		Monument m = null;
		Res = db.executionQuery(
				"select id_monument,name_monument,city,note,description,haversine_distance(latitude,longitude,49,2) from monument where id_monument=2");

		while (Res.next()) {
			m = new Monument(Res.getInt(1), Res.getString(2), Res.getString(3), Res.getInt(4), Res.getString(5),
					Res.getDouble(6));

		}

		return m;

	}

	public CoordinatesDouble positionMonument(int id) throws SQLException {

		ResultSet Res;
		CoordinatesDouble coordinatesDouble = null;
		Res = db.executionQuery("select id_monument,latitude,longitude from monument where id_monument=" + id);

		while (Res.next()) {
			coordinatesDouble = new CoordinatesDouble(new double[] { Res.getDouble(2), Res.getDouble(3) });

		}

		return coordinatesDouble;

	}

	public ArrayList<String> allMonumentType() throws SQLException {

		ResultSet Res;
		ArrayList<String> arrayList = new ArrayList<String>();
		Res = db.executionQuery("select id_monument,type from monument");

		while (Res.next()) {
			arrayList.add(Res.getString(2));
		}

		return arrayList;

	}

	public void persisteEstAmis(int id) throws NumberFormatException, SQLException, YouHaveNoFriendsExeption {

		ResultSet Res, Res1;

		Res = db.executionQuery("select id_user from est_ami where id_user=" + id + " and id_user_Utilisateur=5");
		String test = null;
		if (Res == null)
			throw new YouHaveNoFriendsExeption();
		else
			while (Res.next()) {
				test = Res.getString(1);
			}
		if (test == null) {

			String user1 = null;
			String user2 = null;

			Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
			String dat = dateFormat.format(actuelle);

			Res = db.executionQuery("select userName from utilisateur where id_user=" + id);
			Res1 = db.executionQuery("select userName from utilisateur where id_user=2");

			while (Res.next()) {

				user1 = Res.getString(1);
			}
			while (Res1.next()) {

				user2 = Res1.getString(1);
			}

			Res = db.executionQuery("select count(*) from message");
			int s = 0;

			while (Res.next()) {

				s = Integer.parseInt(Res.getString("count(*)"));
				s++;
			}

			String tab[] = { "" + s, "Vous etes ami avec " + user2, "" + dat };

			db.queryInsert("message", tab);

			String tab1[] = { "" + (s + 1), "Vous etes ami avec " + user1, "" + dat };

			db.queryInsert("message", tab1);

			String tab2[] = { "" + id, "" + 5, "" + s };

			db.queryInsert("est_ami", tab2);

			String tab3[] = { "" + 5, "" + id, "" + (s + 1) };

			db.queryInsert("est_ami", tab3);
		}
	}

	public void persiteAvisite(int id, String text, int note) throws NumberFormatException, SQLException {
		ResultSet Res;
		Res = db.executionQuery("select count(*) from a_visite");
		int s = 0;
		while (Res.next()) {

			s = Integer.parseInt(Res.getString("count(*)"));
			s++;
		}

		String tab[] = { "" + id, "" + 2, "" + note, text };

		db.queryInsert("a_visite", tab);

	}

	public List<Utilisateur> persisteSearchAmi(String recherche) throws SQLException, YouHaveNoFriendsExeption {

		ResultSet Res;
		usersSearch.clear();
		Res = db.executionQuery("select id_user,userName from utilisateur where userName like '%" + recherche + "%'");
		if (Res == null)
			throw new YouHaveNoFriendsExeption();
		else {
			while (Res.next()) {

				Utilisateur u = new Utilisateur(Res.getInt(1), Res.getString(2));

				usersSearch.add(u);
			}
		}
		return usersSearch;
	}

}
