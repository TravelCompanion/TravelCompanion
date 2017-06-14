package com.example.voyage.travelcompanionapp.callwebservice;

import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.example.voyage.travelcompanionapp.model.ApliMonument;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.XML;

import javax.net.ssl.HttpsURLConnection;

import static org.json.XML.toJSONObject;

public class RecupMonument {

    public InputStream istream;
    public String uri;
    JsonParser jsonParser=new JsonParser();



    public ArrayList<ApliMonument> getAlistMonu() {
        ArrayList<ApliMonument>AlistMonu=new ArrayList<ApliMonument>();
        ApliMonument visage=new ApliMonument();
        ApliMonument visage2=new ApliMonument();
        ApliMonument cine= new ApliMonument();
        ApliMonument travail2=new ApliMonument();
        ApliMonument maison= new ApliMonument();
        ApliMonument fac = new ApliMonument();
        ApliMonument travail =new ApliMonument();

        visage.setName("Visage du monde");
        visage.setGeoloc(new LatLng(49.051209, 2.008451));

        visage2.setName("Visage du monde Bis");
        visage2.setGeoloc(new LatLng(49.0497881, 2.0091093));


        cine.setName("Cine");
        cine.setGeoloc(new LatLng(49.048021, 2.012134));
        travail2.setName("Orange 2");
        travail2.setGeoloc(new LatLng(48.836338, 2.306364));
        maison.setName("Maison");
        maison.setGeoloc(new LatLng(49.045975, 2.011040));

        travail.setName("Orange");
        travail.setGeoloc(new LatLng(48.836798, 2.306745));
        fac.setName("Universite");
        fac.setGeoloc( new LatLng(49.042974, 2.084606));

        AlistMonu.add(visage);
        AlistMonu.add(visage2);
        AlistMonu.add(cine);
        AlistMonu.add(maison);
        AlistMonu.add(travail);
        AlistMonu.add(travail2);
        AlistMonu.add(fac);

        return AlistMonu;

    }

    public ArrayList<ApliMonument> getWebServiceMonument() {

        ArrayList<ApliMonument>AlistMonu=new ArrayList<ApliMonument>();

        String descrip="aucune description";
        String name,ville,type;
        int id,note;
        double lat,lon;
        Double[]dist = new Double[2];
        LatLng geoloc;

        try {
            String output=  jsonParser.execute(Configuration.IpDevice()+Configuration.getRestListMonument()).get();
            Log.d("xml",output);

            if (output!=null) {

                JSONArray jArray = new JSONArray("["+output+"]");

                for(int j=0;j<jArray.length();j++){
                    JSONObject json_datavaleur = jArray.getJSONObject(j);
                    Log.d("MonuementProche",json_datavaleur.getString("listMonuementProche"));
                    JSONObject object = new JSONObject(json_datavaleur.toString());
                    JSONArray array = object.getJSONArray("listMonuementProche");
                    for(int k = 0 ; k < array.length() ; k++){
                        ApliMonument monu=new ApliMonument();

                        descrip = array.getJSONObject(k).getString("description");
                        name = array.getJSONObject(k).getString("name_monument");
                        id = array.getJSONObject(k).getInt("id_monument");
                        lat = array.getJSONObject(k).getDouble("latitude");
                        lon = array.getJSONObject(k).getDouble("longitude");
                        dist[0] = array.getJSONObject(k).getDouble("distance");
                        type = array.getJSONObject(k).getString("type");
                        note = array.getJSONObject(k).getInt("note");
                        ville= array.getJSONObject(k).getString("city");
                        geoloc= new LatLng(lat,lon);
                        monu.setName(name);
                        monu.setDescription(descrip);
                        monu.setDistance(dist);
                        monu.setId(id);
                        monu.setNote(note);
                        monu.setGeoloc(geoloc);
                        monu.setVille(ville);

                        AlistMonu.add(monu);


                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return AlistMonu;
    }





}
