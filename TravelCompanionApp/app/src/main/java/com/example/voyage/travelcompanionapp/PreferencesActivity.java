package com.example.voyage.travelcompanionapp;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class PreferencesActivity extends AppCompatActivity implements View.OnClickListener{
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        bfa=(FloatingActionButton)findViewById(R.id.fab_action_pref);


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



        bfa.setOnClickListener(PreferencesActivity.this);





    }

    public void findidCheckbox(){


    }

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
                if (j != 0) {
                        elempref += choose_pref[j] + ",";
                    }
                }

                if(elempref!=""){
                    Log.d("listpref",elempref);
                    Toast.makeText(PreferencesActivity.this, elempref, Toast.LENGTH_SHORT).show();
                    //Utiliser une methode pour envoyer les elements
                }
                else{
                    Log.d("listpref","pas d'element dans la liste");
                }



        }

    }
}
