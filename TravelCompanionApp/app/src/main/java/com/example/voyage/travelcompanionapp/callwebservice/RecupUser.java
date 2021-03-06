package com.example.voyage.travelcompanionapp.callwebservice;


import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.voyage.travelcompanionapp.model.ApliUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecupUser {


    public boolean testUser(String email, String mdp) {
        boolean result = false;
        try {
            String output = null;
            JsonParser jsonParser = new JsonParser();
            output = jsonParser.execute(Configuration.IpDevice() + Configuration.getIdUser(email, mdp)).get();
            if (output != null) {
                String dataoutput = "";

                JSONArray joutput = null;
                try {
                    joutput = new JSONArray("[" + output + "]");

                    for (int j = 0; j < joutput.length(); j++) {
                        JSONObject json_datavaleur = joutput.getJSONObject(j);
                        dataoutput = json_datavaleur.getString("id");
                    }
                    if (!(dataoutput.equals("-1"))) {
                        result = true;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public ApliUser getUser(String email, String mdp) {

        ApliUser appuser = new ApliUser();
        JsonParser jsonParser1 = new JsonParser();
        JsonParser jsonParser2 = new JsonParser();

        try {
            if (this.testUser(email, mdp)) {
                String dataoutput = "";
                String output = jsonParser1.execute(Configuration.IpDevice() + Configuration.getIdUser(email, mdp)).get();
                JSONArray joutput = new JSONArray("[" + output + "]");
                for (int j = 0; j < joutput.length(); j++) {
                    JSONObject json_datavaleur = joutput.getJSONObject(j);
                    dataoutput = json_datavaleur.getString("id");
                }
                appuser.setId(dataoutput);
                String user = jsonParser2.execute(Configuration.IpDevice() + Configuration.getRestUser(dataoutput)).get();
                JSONArray jArray = new JSONArray("[" + user + "]");

                for (int k = 0; k < jArray.length(); k++) {
                    JSONObject json_datatable = jArray.getJSONObject(k);
                    String datalist = json_datatable.getString("list");
                    JSONArray array = new JSONArray("[" + datalist + "]");
                    for (int j = 0; j < array.length(); j++) {
                        JSONObject json_datavaleur = array.getJSONObject(j);
                        Log.d("utilisateur", json_datavaleur.getString("email"));
                        appuser.setEmail(json_datavaleur.getString("email"));
                        appuser.setPreferences(json_datavaleur.getString("preferences"));
                        appuser.setUsername(json_datavaleur.getString("userName"));

                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        return appuser;
    }

    public boolean inscription(String username,String mail, String mdp,String typepref) {
        boolean repinscript = false;

            boolean result = false;
            try {
                String outputupdate = null;
                JsonParser callwebservice = new JsonParser();
                String urlcall=Configuration.IpDevice() + Configuration.inscription(username,mail,mdp,typepref);
                outputupdate = callwebservice.execute(urlcall).get();
                if (outputupdate != null) {
                    String dataoutput = "";
                    JSONArray joutput = null;
                    joutput = new JSONArray("[" + outputupdate + "]");
                    for (int j = 0; j < joutput.length(); j++) {
                        JSONObject json_datavaleur = joutput.getJSONObject(j);
                        dataoutput = json_datavaleur.getString("result");
                    }
                    if (dataoutput.equals("true")) {
                        result = true;
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;



    }


    public boolean validateEmail(String mail) {
        boolean result = false;
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern p = Pattern.compile(EMAIL_PATTERN);
        Matcher m = p.matcher(mail);
        if (m.matches()) {
            result = true;
        }

        return result;
    }


    public boolean updateuser(int id, String pref) {
        boolean result = false;
        try {
            String outputupdate = null;
            JsonParser callwebservice = new JsonParser();
            String urlcall=Configuration.IpDevice() + Configuration.updateuser(id, pref);
            outputupdate = callwebservice.execute(Configuration.IpDevice() + Configuration.updateuser(id, pref)).get();
            if (outputupdate != null) {
                String dataoutput = "";
                JSONArray joutput = null;
                    joutput = new JSONArray("[" + outputupdate + "]");
                    for (int j = 0; j < joutput.length(); j++) {
                        JSONObject json_datavaleur = joutput.getJSONObject(j);
                        dataoutput = json_datavaleur.getString("result");
                    }
                    if (dataoutput.equals("true")) {
                        result = true;
                    }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}

