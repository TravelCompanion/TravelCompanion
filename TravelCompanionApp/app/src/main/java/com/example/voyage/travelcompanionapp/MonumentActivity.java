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

        if (config.smallestScreenWidthDp >= 600)
        {
            activity_select= R.layout.tablet_activity_content_monument;
        }
        else{
            activity_select= R.layout.activity_content_monument;
        }
        setContentView(activity_select);
        Toolbar bar_monument_info=(Toolbar)findViewById(R.id.toolbar_monumentActivity);
        visite = (Button) findViewById(R.id.button_visite);

        setSupportActionBar(bar_monument_info);
        setActionbarBackable();
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_content_monument);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, bar_monument_info, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();






        TextView descMonu=(TextView)findViewById(R.id.textView_descmonu);
        descMonu.setText("Le Lorem Ipsum est simplement du faux texte employé dans la composition " +
                "et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de" +
                " l'imprimerie depuis les années 1500, quand un peintre anonyme assembla ensemble des" +
                " morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait " +
                "que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans" +
                " que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la " +
                "vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par " +
                "son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.");
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
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(false);
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
            //.setView(inflater.inflate(R.layout.activity_rate, (ViewGroup) findViewById(R.id.layout_rate_bar) ));

            ratingBar = (RatingBar) mView.findViewById(R.id.ratingBar_note);




            alertDialogBuilder.setPositiveButton(R.string.note,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            addListenerOnRatingBar(ratingBar);

                            // if this button is clicked, close
                            // current activity
                            //Toast.makeText(MonumentActivity.this, "Note: "+String.valueOf(rating), Toast.LENGTH_SHORT).show();

                            MonumentActivity.this.finish();
                        }
                    })
                    .setNegativeButton(R.string.fermer,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
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




}
