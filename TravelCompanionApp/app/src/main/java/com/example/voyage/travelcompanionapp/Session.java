package com.example.voyage.travelcompanionapp;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.voyage.travelcompanionapp.conversionapi.TheoricPlaceConvertionApli;
import com.example.voyage.travelcompanionapp.conversionapi.TheoricUserConvertionApli;
import com.example.voyage.travelcompanionapp.model.ApliUser;

public class Session {
    //cette classe est un singleton afin quel soit unique
        // Shared Preferences
        SharedPreferences sharedpreferences;
        private static Session session;
        // Editor pour le Shared preferences
        Editor editor;
    int PRIVATE_MODE = 0;
    public static final TheoricUserConvertionApli USER_CONVERTION_APLI = new TheoricUserConvertionApli();
    public static final TheoricPlaceConvertionApli PLACE_CONVERTION_APLI = new TheoricPlaceConvertionApli();
    public static boolean refpage=false;

    private static final String PREF_NAME = "PrefSession";

    private static final String IS_LOGIN = "IsLoggedIn";


    public static final String KEY_NAME = "name";

    //je rends ces variables public afin qu'on puisse y acceder a l'exterieur
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PREFUSER = "types_preferences";
    public static ApliUser appuser;

    public  HashMap<String, String> userHashMap = new HashMap<String, String>();

        // Context
        Context _context;

    public static Session getSession(Context context) {
        if(session==null){
            session=new Session(context);
        }
        return session;
    }


    // Constructor
        /*public Session(Context context){
            this._context = context;
            preferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = preferences.edit();
        }*/

        private Session(Context context) {
            this._context = context;
            sharedpreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = sharedpreferences.edit();
        }

        /**
         * Creation de la session
         * */
        public void createLoginSession( String email){
            // Storing login value as TRUE
            editor.putBoolean(IS_LOGIN, true);

            editor.putString(KEY_EMAIL, email);

            // commit changes
            editor.commit();
        }


        /**
         * Verifie si utilisateur est connecté
         * si c'est le cas on retourne sur la page de connexion
         * Else won't do anything
         * */
        public void checkLogin(){
            // verifie le status de l'utilisateur
            if(!this.isLoggedIn()){
                // l'utilisateur n'est pas connecté redirection sur Login Activity
                Intent i = new Intent(_context, LoginActivity.class);
                // fermeture de toute les page
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                // ajout d'un nouveau drapeau pour une nouvelle activity
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // lancement de la page login activity
                _context.startActivity(i);
            }

        }



        /**
         * cette methode enregistre les element de l'utilisateur dans un hashMap
         * */
        public HashMap<String, String> getUserDetails(){

            userHashMap.put(KEY_EMAIL, sharedpreferences.getString(KEY_EMAIL, null));

            return userHashMap;
        }

    public void updateHashMapUserDetails(String KEY,String val){
        if(isLoggedIn()) {
            //always commmit before put value in hashMap
            editor.putString(KEY, val);
            editor.commit();
            userHashMap.put(KEY, sharedpreferences.getString(KEY, null));

        }

    }

        /**
         * purge la session
         * */
        public void logoutUser(){
            // Clearing all data from Shared Preferences
            session =null;
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
         * methode l'etat de l'utilsateur
         * **/
        // Get Login State
        public boolean isLoggedIn(){
            return sharedpreferences.getBoolean(IS_LOGIN, false);
        }

    public HashMap<String, String> getUserHashMap() {
        return userHashMap;
    }
    }



