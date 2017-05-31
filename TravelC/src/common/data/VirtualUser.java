package common.data;

import java.util.ArrayList;
import java.util.HashMap;

import common.type.PlaceType;
import common.type.TypeConfiguration;
import tools.math.CoordinatesDouble;
import tools.parse.StringParseGenerable;
import tools.parse.StringParseLoggable;
import tools.parse.StringParser;

public class VirtualUser implements StringParseGenerable<VirtualUser, String>, StringParseLoggable {
	/**
	 * Its a transition class between business class and IO class for
	 * users'datas
	 */

	private String id;
	private CoordinatesDouble position;
	private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();
	private ArrayList<VirtualUser> friendList = new ArrayList<VirtualUser>();
	private ArrayList<VirtualPlace> visited = new ArrayList<VirtualPlace>();

	public ArrayList<VirtualUser> getFriendList() {
		return friendList;
	}

	public VirtualUser() {
	}

	public VirtualUser(String id, CoordinatesDouble position) {
		this.id = id;
		this.position = position;

	}

	public VirtualUser(String id, CoordinatesDouble position, HashMap<PlaceType, Double> preferences) {
		this.id = id;
		this.position = position;
		this.preferences = preferences;
	}

	public void newFriend(VirtualUser user) {
		friendList.add(user);
	}

	public String getId() {
		return id;
	}

	public HashMap<PlaceType, Double> getPreferences() {
		return preferences;
	}

	public CoordinatesDouble getPosition() {
		return position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((preferences == null) ? 0 : preferences.hashCode());
		return result;
	}
	
	public ArrayList<VirtualPlace> getVisited() {
		return visited;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VirtualUser other = (VirtualUser) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (preferences == null) {
			if (other.preferences != null)
				return false;
		} else if (!preferences.equals(other.preferences))
			return false;
		return true;
	}

	public String toString() {
		return "VirtualUser [id=" + id + ", position=" + position + ", preferences=" + preferences + "]";
	}

	// Parsing
	// ================================================================================================

	public String toLog() {
		String log = id + "," + position.getX() + "," + position.getY();
		log += ",";
		int k = 0;
		for (Double n : preferences.values()) {

			log += k == 0 ? n : "/" + n;
			k++;
		}
		if (!friendList.isEmpty()) {
			log += ",";
			k = 0;
			for (VirtualUser user : friendList) {

				log += k == 0 ? user.getId() : "/" + user.getId();
				k++;
			}
		}
		return log + ';';
	}

	public VirtualUser generateItem(ArrayList<String> args) {
		this.id = args.get(0);
		this.position = new CoordinatesDouble(
				new double[] { Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)) });

		ArrayList<String> lines = StringParser.sliceLine(args.get(3), '/');
		for (int i = 0; i < TypeConfiguration.number; i++)
			this.preferences.put(TypeConfiguration.get(i), Double.parseDouble(lines.get(i)));
		if (args.size() < 4) {
			lines = StringParser.sliceLine(args.get(4), '/');
			for (String name : lines)
				this.friendList.add(VirtualDataBase.getUser(name));
		}

		return this;
	}

	public StringParseGenerable<VirtualUser, String> init() {
		return new VirtualUser();
	}

	public String getStringKey() {
		return id;
	}

	public String getKey() {
		return id;
	}

}
