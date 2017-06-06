package com.example.voyage.travelcompanionapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
     CheckBox chkmusee;
     CheckBox chkmaison;
     CheckBox chkparc;
     CheckBox chkeglise;
     CheckBox chkjardin;
     CheckBox chkattraction;
     CheckBox chkvilla;
     CheckBox chkpavillon;
     CheckBox chkcaves;
     CheckBox chksocle;
     CheckBox chkauberge;
     CheckBox chkhotel;
     CheckBox chkimmeuble;
     CheckBox chkchateau;
    String elempref="";
    FloatingActionButton bfa;
     String[] choose_pref=new String[14];
     Switch switch_all;
    Toolbar toolbar;
    Session session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //liaison avec le XML
        setContentView(R.layout.activity_preferences);
        session = Session.getSession(getApplicationContext());

        //affectation des elements du fichier xml à des variable définis
        bfa=(FloatingActionButton)findViewById(R.id.fab_action_pref);
        switch_all = (Switch) findViewById(R.id.switch_select_all_checkbox);
        toolbar = (Toolbar) findViewById(R.id.toolbar_preferences);
        chkmusee=(CheckBox) findViewById(R.id.checkBox_musee);
        chkmaison=(CheckBox) findViewById(R.id.checkBox_maison);
        chkparc=(CheckBox) findViewById(R.id.checkBox_parc);
        chkeglise=(CheckBox) findViewById(R.id.checkBox_eglise);
        chkjardin=(CheckBox) findViewById(R.id.checkBox_jardin);
        chkattraction=(CheckBox) findViewById(R.id.checkBox_attraction);
        chkvilla=(CheckBox) findViewById(R.id.checkBox_villa);
        chkpavillon=(CheckBox) findViewById(R.id.checkBox_pavillon);
        chkcaves=(CheckBox) findViewById(R.id.checkBox_caves);
        chksocle=(CheckBox) findViewById(R.id.checkBox_socle);
        chkauberge=(CheckBox) findViewById(R.id.checkBox_auberge);
        chkhotel=(CheckBox) findViewById(R.id.checkBox_hotel);
        chkimmeuble=(CheckBox) findViewById(R.id.checkBox_immeuble);
        chkchateau=(CheckBox) findViewById(R.id.checkBox_chateau);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //action quand le bouton(switch) change.Appel de la méthode onCheckedChanged
        switch_all.setOnCheckedChangeListener(PreferencesActivity.this);
        //action quand on click sur  bouton.Appel de la méthode onClick
        bfa.setOnClickListener(PreferencesActivity.this);



    }


    //quand on click sur le floating bouton il prend en compte les checkbox selectionnées(avec des 1) et les affecte à un tableau
    @Override
    public void onClick(View v) {
        for (int i = 0; i <= 13; i++){
            choose_pref[i] ="0";
        }

      if (v== bfa){

            if (chkmusee.isChecked()) {
                choose_pref[0] = "1";
            }
            if (chkeglise.isChecked()) {
                choose_pref[1] = "1";
            }
            if (chkattraction.isChecked()) {
                choose_pref[2] = "1";
            }
            if (chkjardin.isChecked()) {
                choose_pref[3] = "1";
            }

            if (chkchateau.isChecked()) {
                choose_pref[4] = "1";
            }

            if (chkparc.isChecked()) {
                choose_pref[5] = "1";
            }
            if (chkimmeuble.isChecked()) {
                choose_pref[6] = "1";
            }
            if (chkhotel.isChecked()) {
                choose_pref[7] = "1";
            }

            if (chkauberge.isChecked()) {
                choose_pref[8] = "1";
            }
            if (chksocle.isChecked()) {
                choose_pref[9] = "1";
            }

            if (chkmaison.isChecked()) {
                choose_pref[10] = "1";
            }
            if (chkcaves.isChecked()) {
                choose_pref[11] = "1";
            }
            if (chkpavillon.isChecked()) {
                choose_pref[12] = "1";
            }
            if (chkvilla.isChecked()) {
                choose_pref[13] = "1";
            }
          elempref="";

                for (int j = 0; j<= choose_pref.length-1; j++) {
                if (j != 0 || j!=choose_pref.length-1) {
                        elempref += choose_pref[j] + ",";
                    }

                    if (j==choose_pref.length-1) {
                        elempref += choose_pref[j] + "";
                    }
                }

                if(elempref!=""){
                    Log.d("listpref",elempref);
                    session.updateHashMapUserDetails("types_preferences",elempref);
                    Toast.makeText(PreferencesActivity.this,"preferences sauvegardés "+session.getUserHashMap().get(Session.KEY_PREFUSER), Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("listpref","pas d'element dans la liste");
                }



        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            chkmusee.setChecked(true);
            chkmaison.setChecked(true);
            chkparc.setChecked(true);
            chkeglise.setChecked(true);
            chkjardin.setChecked(true);
            chkattraction.setChecked(true);
            chkvilla.setChecked(true);
            chkpavillon.setChecked(true);
            chkcaves.setChecked(true);
            chksocle.setChecked(true);
            chkauberge.setChecked(true);
            chkhotel.setChecked(true);
            chkimmeuble.setChecked(true);
            chkchateau.setChecked(true);


        } if(isChecked==false) {
            chkmusee.setChecked(false);
            chkmaison.setChecked(false);
            chkparc.setChecked(false);
            chkeglise.setChecked(false);
            chkjardin.setChecked(false);
            chkattraction.setChecked(false);
            chkvilla.setChecked(false);
            chkpavillon.setChecked(false);
            chkcaves.setChecked(false);
            chksocle.setChecked(false);
            chkauberge.setChecked(false);
            chkhotel.setChecked(false);
            chkimmeuble.setChecked(false);
            chkchateau.setChecked(false);        }

    }
}
