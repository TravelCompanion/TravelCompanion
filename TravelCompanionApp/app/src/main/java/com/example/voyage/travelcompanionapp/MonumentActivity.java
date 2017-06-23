package com.example.voyage.travelcompanionapp;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.InterpolatorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voyage.api.api.data.TheoricMainUser;
import com.example.voyage.api.api.data.TheoricPlace;
import com.example.voyage.api.api.data.TheoricUser;
import com.example.voyage.api.api.ia.IAManager;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.common.type.TypeSafeMemory;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.tools.math.compare.CompareUnitDouble;
import com.example.voyage.travelcompanionapp.callwebservice.RecupMonument;
import com.example.voyage.travelcompanionapp.callwebservice.RecupUser;
import com.example.voyage.travelcompanionapp.conversionapi.TheoricPlaceConvertionApli;
import com.example.voyage.travelcompanionapp.conversionapi.TheoricUserConvertionApli;
import com.example.voyage.travelcompanionapp.model.ApliMonument;
import com.example.voyage.travelcompanionapp.model.ApliUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class MonumentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener  {

    private DrawerLayout drawer;
    Button visite;
    Bundle savedInstanceState;
    final Context context = this;
    RatingBar ratingBar;
    Session session;

    public Note note_monu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PrefDistance", 0);
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        session = Session.getSession(getApplicationContext());

        int activity_select;
        String description;

        if (config.smallestScreenWidthDp >= 600)
        {
            activity_select= R.layout.tablet_content_monument;
        }
        else{
            activity_select= R.layout.content_monument;
        }


        setContentView(activity_select);
        Toolbar bar_monument_info=(Toolbar)findViewById(R.id.toolbar_monumentActivity);
        setSupportActionBar(bar_monument_info);
        visite = (Button) findViewById(R.id.button_visite);

        setActionbarBackable();
        description=pref.getString("description",null);
        if("".equals(pref.getString("description",null))){
            description="aucune description";
        }

        TextView descMonu=(TextView)findViewById(R.id.textView_descmonu);
        descMonu.setText(description);
        TextView distMonu=(TextView)findViewById(R.id.textView_distance);
        String distance=pref.getString("distance", null);

        distMonu.setText("Distance: " + distance + " m");



        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        visite.setOnClickListener(MonumentActivity.this);



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void setActionbarBackable() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==visite){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    MonumentActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();

            // set title
            alertDialogBuilder.setTitle(R.string.donner_note);
            View mView = getLayoutInflater().inflate(R.layout.activity_rate,null);

            // set dialog message
            alertDialogBuilder
                    .setCancelable(false)
                    .setView(mView);

            ratingBar = (RatingBar) mView.findViewById(R.id.ratingBar_note);


            alertDialogBuilder.setPositiveButton(R.string.note,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            addListenerOnRatingBar(ratingBar);

                            MonumentActivity.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.fermer,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.show();

        }
    }

    public void addListenerOnRatingBar(RatingBar r) {

        note_monu = new Note();
        String rate;

        rate=String.valueOf(Integer.valueOf((int) r.getRating()));
        Log.d("user_Note",String.valueOf(rate));
        note_monu.setNote(r.getRating());
        r.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingB, float rating,
                                        boolean fromUser) {

                Log.d("user_Notes",String.valueOf(rating));

            }
        });
        if (session.refpage==true){
            TheoricUser testlearn = testlearn(session.USER_CONVERTION_APLI.convertFrom(session.appuser), note_monu.getNote());
            Log.d("user_class_note", String.valueOf(note_monu.getNote()));
            ApliUser appuserIa = session.USER_CONVERTION_APLI.convertTo(testlearn);
            appuserIa.setEmail(session.appuser.getEmail());
            appuserIa.setId(session.appuser.getId());
            appuserIa.setPosition(session.appuser.getPosition());
            appuserIa.setPass(session.appuser.getPass());
            appuserIa.setFriends(session.appuser.getFriends());
            session.appuser = appuserIa;
            Log.d("IAuserPreferen_user", session.appuser.getPreferences().values().toString());
            String listprefuseria = session.appuser.getPreferences().values().toString().replaceAll("\\[|\\]", "");
            Log.d("IAuserPreference_user", listprefuseria);
            RecupUser user = new RecupUser();
            boolean result;
            result=user.updateuser(Integer.valueOf(session.appuser.getId()), listprefuseria);
            Log.d("result",String.valueOf(result));
        }



    }

    public TheoricUser testlearn(TheoricUser theoricUser,double note){
        TheoricUser resultIa=IAManager.learn(theoricUser,note);
        return resultIa;

    }



    public  static   ArrayList<ApliMonument> requestSuggest(ApliUser apliUser, ArrayList<ApliMonument> monuments){
        TypeConfiguration.getConfig(new TypeSafeMemory());
        TheoricUserConvertionApli userConvertionApli = new TheoricUserConvertionApli();
        TheoricPlaceConvertionApli placeConvertionApli = new TheoricPlaceConvertionApli();

        TheoricUser theoricUser = userConvertionApli.convertFrom(apliUser);
        ArrayList<TheoricPlace> places = new ArrayList<TheoricPlace>();
        for(ApliMonument apliMonument : monuments)
            places.add(placeConvertionApli.convertFrom(apliMonument));

        ArrayList<CompareUnitDouble<TheoricPlace>> result = IAManager.choosePlaces(theoricUser, places);
        ArrayList<ApliMonument> apliMonuments = new ArrayList<ApliMonument>();

        for(CompareUnitDouble<TheoricPlace> cud : result)
            apliMonuments.add(placeConvertionApli.convertTo(cud.getElement()));
        for(ApliMonument monument : apliMonuments) {
            monument.setDescription(RecupMonument.monumentHashMap.get(monument.getId()).getDescription());
            monument.setGeoloc(RecupMonument.monumentHashMap.get(monument.getId()).getGeoloc());
        }
        return apliMonuments;
    }

}
