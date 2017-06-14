package com.example.voyage.api.data;

import com.example.voyage.api.tools.list.FileStruct;
import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.api.tools.math.Matrix;

public class TheoricUser {
	private String userName;
	private CoordinatesDouble position;
	private FileStruct<CoordinatesDouble> visitedRecently = new FileStruct<CoordinatesDouble>();
	protected Matrix preferences;

	public TheoricUser() {

	}

	public TheoricUser(String userName, Matrix pref) {
		this.userName = userName;
		this.preferences = pref;
	}

	public TheoricUser(String userName,CoordinatesDouble position ,Matrix pref) {
		this.userName = userName;
		this.preferences = pref;
		this.position = position;
	}

	public TheoricUser(String userName) {
		this.userName = userName;
	}

	public CoordinatesDouble getPosition() {
		return position;
	}

	public void moveTo(CoordinatesDouble newPosistion) {
		this.position = newPosistion;
	}

	public boolean hasVisited(CoordinatesDouble place) {
		return visitedRecently.contains(place);
	}

	public String toString() {
		return "TheoricUser [position=" + position + ", preferences=" + preferences + "]";
	}

	public Matrix getPreferences() {
		return preferences;
	}

	public String getUserName() {
		return userName;
	}

}
