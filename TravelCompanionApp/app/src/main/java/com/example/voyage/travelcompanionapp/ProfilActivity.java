package com.example.voyage.travelcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.voyage.travelcompanionapp.R;

public class ProfilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();
        ListView list_avis_monument=(ListView)findViewById(R.id.list_avis_monument);

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Avis");
        spec.setContent(R.id.tab_avis);
        spec.setIndicator("Avis");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Preferences");
        spec.setContent(R.id.tab_pref);
        spec.setIndicator("Preferences");
        host.addTab(spec);
        final String[]data_notice= new String[]{"monument1","monument2","monument3"};
        ArrayAdapter<String> listadaptater;
        listadaptater= new ArrayAdapter<String>(ProfilActivity.this,android.R.layout.simple_list_item_1,data_notice);
        list_avis_monument.setAdapter(listadaptater);

        list_avis_monument.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id){
                for(int i=0;i<data_notice.length;i++){
                    if(position==i){
                        Toast.makeText(ProfilActivity.this,data_notice[i].toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


/*
       Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab Three");
        host.addTab(spec);*/
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_room) {
            Intent intent = new Intent(ProfilActivity.this, MapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(ProfilActivity.this, ProfilActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


