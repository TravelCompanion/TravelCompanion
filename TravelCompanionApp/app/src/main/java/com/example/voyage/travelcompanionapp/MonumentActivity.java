package com.example.voyage.travelcompanionapp;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
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

import com.example.voyage.api.api.data.TheoricPlace;
import com.example.voyage.api.api.data.TheoricUser;
import com.example.voyage.api.api.ia.IAManager;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.tools.math.compare.CompareUnitDouble;
import com.example.voyage.travelcompanionapp.model.ApliMonument;
import com.example.voyage.travelcompanionapp.model.ApliUser;

import java.util.ArrayList;

public class MonumentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener  {

    private DrawerLayout drawer;
    Button visite;
    Bundle savedInstanceState;
    final Context context = this;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PrefDistance", 0);
        super.onCreate(savedInstanceState);
        Configuration config = getResources().getConfiguration();
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
        setActionbarBackable();
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

        Note note_monu = new Note();
        String rate;

        rate=String.valueOf(Integer.valueOf((int) r.getRating()));
        Log.d("user_Note",String.valueOf(rate));
        note_monu.setNote(r.getRating());
        r.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingB, float rating,
                                        boolean fromUser) {

                Log.d("user_Note",String.valueOf(rating));

            }
        });
    }



   /* public  static  void requestSuggest(ApliUser apliUser, ArrayList<ApliMonument> monuments){
        TheoricUser tu = convertTo(ApliUser);
        ArrayList<TheoricPlace> tps = new ArrayList<TheoricPlace>();
        for(ApliMonument apliMonument : monuments)
            tps.add(convertTo(apliMonument));
        ArrayList<CompareUnitDouble<TheoricPlace>> result = IAManager.choosePlaces(TheoricUser tu, ArrayList<TheoricPlace> tps);
               //CompareUnitDouble<T>   CompareUnitDouble<TheoricPlace>
               //        T element;         TheoricPlace element;
               //        double value;      double value;
               //TheoricPlace t = result.get(0).getElement();
            //IAManager.selectPlace(0,result);
        //IAManager.shortLearn(tu,tp);
        //IAManager.learn(tu,tp,note);
        //return result;
        for(CompareUnitDouble<TheoricPlace> cud : result)
            list.add(convertfrom(cud.getElement()));
    }*/

}
