package com.example.voyage.travelcompanionapp.persistance;

import com.example.voyage.travelcompanionapp.model.Monument;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RecupMonument {

    public ArrayList<Monument> getAlistMonu(){
        ArrayList<Monument>AlistMonu=new ArrayList<Monument>();
        Monument visage=new Monument();
        Monument visage2=new Monument();
        Monument cine= new Monument();
        Monument travail2=new Monument();
        Monument maison= new Monument();
        Monument fac = new Monument();
        Monument travail =new Monument();

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





}
