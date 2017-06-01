package com.example.voyage.travelcompanionapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

public class PreferencesActivity extends AppCompatActivity {
    private CheckBox chkmusee;
    private CheckBox chkmaison;
    private CheckBox chkparc;
    private CheckBox chkeglise;
    private CheckBox chkjardin;
    private CheckBox chkattraction;
    private CheckBox chkvilla;
    private CheckBox chkpavillon;
    private CheckBox chkcaves;
    private CheckBox chksocle;
    private CheckBox chkauberge;
    private CheckBox chkhotel;
    private CheckBox chkimmeuble;
    private CheckBox chkchateau;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

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




    }

    public void findidCheckbox(){


    }
}
