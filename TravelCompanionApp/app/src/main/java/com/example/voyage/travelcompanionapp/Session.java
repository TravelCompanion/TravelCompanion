package com.example.voyage.travelcompanionapp;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Session {
        // Shared Preferences
        SharedPreferences preferences;
        private static Session session;
        // Editor for Shared preferences
        Editor editor;

        // Context
        Context _context;

    public static Session getSession(Context context) {
        if(session==null){
            session=new Session(context);
        }
        return session;
    }

    int PRIVATE_MODE = 0;


        private static final String PREF_NAME = "PrefSession";

        private static final String IS_LOGIN = "IsLoggedIn";


        public static final String KEY_NAME = "name";

        // Email address (make variable public to access from outside)
        public static final String KEY_EMAIL = "email";

        // Constructor
        /*public Session(Context context){
            this._context = context;
            preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = preferences.edit();
        }*/

        private Session(Context context) {
            this._context = context;
            preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = preferences.edit();
        }

        /**
         * Create login session
         * */
        public void createLoginSession( String email){
            // Storing login value as TRUE
            editor.putBoolean(IS_LOGIN, true);

            editor.putString(KEY_EMAIL, email);

            // commit changes
            editor.commit();
        }

        /**
         * Check login method wil check user login status
         * If false it will redirect user to login page
         * Else won't do anything
         * */
        public void checkLogin(){
            // Check login status
            if(!this.isLoggedIn()){
                // user is not logged in redirect him to Login Activity
                Intent i = new Intent(_context, LoginActivity.class);
                // Closing all the Activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // Add new Flag to start new Activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // Staring Login Activity
                _context.startActivity(i);
            }

        }



        /**
         * Get stored session data
         * */
        public HashMap<String, String> getUserDetails(){
            HashMap<String, String> user = new HashMap<String, String>();

            // user email id
            user.put(KEY_EMAIL, preferences.getString(KEY_EMAIL, null));

            // return user
            return user;
        }

        /**
         * Clear session details
         * */
        public void logoutUser(){
            // Clearing all data from Shared Preferences
            editor.clear();
            editor.commit();

            // After logout redirect user to Loing Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

        /**
         * Quick check for login
         * **/
        // Get Login State
        public boolean isLoggedIn(){
            return preferences.getBoolean(IS_LOGIN, false);
        }
    }

