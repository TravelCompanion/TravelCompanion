package com.example.voyage.travelcompanionapp.persistance;

import com.example.voyage.api.common.convertion.iabdd.TheoricMonumentConverter;
import com.example.voyage.api.common.convertion.iabdd.TheoricUserConverter;
import com.example.voyage.api.common.data.NoPlaceFoundException;
import com.example.voyage.api.data.TheoricPlace;
import com.example.voyage.api.data.TheoricUser;
import com.example.voyage.api.ia.IAManager;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.persistence.PersistenceData;
import com.example.voyage.api.tools.math.compare.CompareUnitDouble;
import com.example.voyage.travelcompanionapp.model.ApliMonument;
import com.google.android.gms.maps.model.LatLng;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecupMonument {

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
    /*public static ArrayList<ApliMonument> requestSuggestion()throws SQLException, NoPlaceFoundException{
        PersistenceData p = new PersistenceData("user","pwd","database");
        TheoricUserConverter theoricUserConverter = new TheoricUserConverter();
        TheoricMonumentConverter theoricPlaceConverter = new TheoricMonumentConverter();

        user = theoricUserConverter.toDataBase(apliUser);
        ArrayList<TheoricPlace> places = new ArrayList<TheoricPlace>();
        ArrayList<Monument> monuments =  (ArrayList<Monument>)p.loadMonument(0.0,0.0,5.0);
        for(Monument monument: monuments)
        {
            places.add(theoricPlaceConverter.fromDatabase(monument))
        }
        ArrayList<CompareUnitDouble<TheoricPlace>> list = IAManager.choosePlaces( user,places);
        ArrayList<ApliMonument> apliMonuments = new ArrayList<ApliMonument>();
        for(CompareUnitDouble<TheoricPlace> unit : list)
            apliMonuments.add(theoricPlaceConverter.toDataBase(unit.getElement()));
        return null;
    }*/




}
