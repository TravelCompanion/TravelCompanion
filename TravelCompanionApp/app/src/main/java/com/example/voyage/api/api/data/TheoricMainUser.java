package com.example.voyage.api.api.data;


import com.example.voyage.api.tools.math.Matrix;

public class TheoricMainUser extends TheoricUser {

	public TheoricMainUser() {
		// TODO Auto-generated constructor stub
	}

	public TheoricMainUser(int id,String userName, Matrix pref) {
		super(id,userName, pref);
	}

	public TheoricMainUser(String userName) {
		super(userName);
	}

	public void updatePref(Matrix weights) {
		this.preferences = weights;
	}

	public int getId() {
		return id;
	}

}
