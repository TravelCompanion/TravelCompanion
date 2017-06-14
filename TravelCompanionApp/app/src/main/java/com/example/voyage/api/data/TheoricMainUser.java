package com.example.voyage.api.data;


import com.example.voyage.api.tools.math.Matrix;

public class TheoricMainUser extends TheoricUser {
	public TheoricMainUser() {
		// TODO Auto-generated constructor stub
	}

	public TheoricMainUser(String userName, Matrix pref) {
		super(userName, pref);
	}

	public TheoricMainUser(String userName) {
		super(userName);
	}

	public void updatePref(Matrix weights) {
		this.preferences = weights;
	}

}