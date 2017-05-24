package api.externalData;

import java.util.ArrayList;
import java.util.HashMap;

import api.cte.PlaceType;
import api.ia.TheoricUser;
import tools.math.CoordinatesDouble;
import tools.parse.StringParseGenerable;
import tools.parse.StringParseLoggable;

public class VirtualUser implements StringParseGenerable<VirtualUser, String>, StringParseLoggable {
	/**
	 * Its a transition class between business class and IO class for
	 * users'datas
	 */

	private String id;
	private CoordinatesDouble position;
	private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();

	public VirtualUser() {
	}

	public VirtualUser(String id, CoordinatesDouble position) {
		this.id = id;
		this.position = position;

	}

	public String toLog() {
		String log = id + "," + position.getX() + "," + position.getY();
		for (Double n : preferences.values())
			log += "," + n;
		return log + ';';
	}

	public VirtualUser generateItem(ArrayList<String> args) {
		this.id = args.get(0);
		this.position = new CoordinatesDouble(
				new double[] { Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2)) });
		int i = 3;
		for (PlaceType type : PlaceType.values()) {
			this.preferences.put(type, Double.parseDouble(args.get(i)));
			i++;
		}
		return this;
	}

	public static TheoricUser toUser(VirtualUser virtualUser) {
		TheoricUser user = new TheoricUser(virtualUser.id, virtualUser.position);
		for (PlaceType type : virtualUser.preferences.keySet())
			user.getPreferences().put(type, virtualUser.preferences.get(type));
		return user;
	}

	public static VirtualUser fromUser(TheoricUser user) {
		VirtualUser virtualUser = new VirtualUser(user.getId(), user.getPosition());
		for (PlaceType type : user.getPreferences().keySet())
			virtualUser.preferences.put(type, user.getPreferences().get(type));
		return virtualUser;
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

}
