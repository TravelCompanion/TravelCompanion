package com.example.voyage.travelcompanionapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.travelcompanionapp.model.ApliMonument;
import com.example.voyage.travelcompanionapp.model.ApliUser;
import com.example.voyage.travelcompanionapp.callwebservice.RecupMonument;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import com.example.voyage.api.externalData.VirtualUser;


public class MapsActivitySuggest extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
    private GoogleApiClient mGoogleApiClient;
    Location location;
    ListView list_monument;
    Session session;
    SharedPreferences preferences;
    int PRIVATE_MODE = 0;
    public static final String KEY_NAME_MONUMENT = "monument_name";
    public static final String KEY_ID_MONUMENT = "id";
    public static final String KEY_DIST_MONUMENT = "distance";
    ApliUser appliuser = new ApliUser();
    private static final String PREF_NAME = "PrefDistance";

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    String emailname="";
    private DrawerLayout drawer;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_map_suggest);
        list_monument = (ListView) findViewById(R.id.list_monuments);
        session = Session.getSession(getApplicationContext());
        session.checkLogin();

        HashMap<String, String> userSession = session.getUserDetails();
        // emailname
        emailname = userSession.get(Session.KEY_EMAIL);

        Toast.makeText(getApplicationContext(), "vous etes connecté: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        getCoord();




        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_map);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_map);
        navigationView.setNavigationItemSelectedListener(this);


    }
    //methode coordonnée gps

    public void getCoord() {
        if (ContextCompat.checkSelfPermission(MapsActivitySuggest.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MapsActivitySuggest.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MapsActivitySuggest.this, "permission accordée", Toast.LENGTH_SHORT).show();

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


            if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {
                Criteria critere = new Criteria();
                critere.setAccuracy(Criteria.ACCURACY_FINE);
                List<String> providers = locationManager.getProviders(true);
                for (String provider : providers) {

                    if (location == null) {
                        locationManager.requestLocationUpdates(provider, MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, new LocationListener() {

                                    @Override
                                    public void onStatusChanged(String provider, int status, Bundle extras) {

                                    }

                                    @Override
                                    public void onProviderEnabled(String provider) {

                                    }

                                    @Override
                                    public void onProviderDisabled(String provider) {

                                    }

                                    @Override
                                    public void onLocationChanged(Location location) {

                                    }
                                });

                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager.getLastKnownLocation(provider);
                            if (location != null) {
                                Log.d("GPS Activé", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
                                appliuser = new ApliUser (emailname,new CoordinatesDouble(new double[]{location.getLatitude(),location.getLongitude()}));
                                Session.appuser = appliuser;
                            }
                        }
                    }
                }

            } else if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (this.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && this.checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        //Permission refusée je ne fais rien
                    } else {
                        location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                    }
                } else {
                    location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                }
            } else {
                Toast.makeText(MapsActivitySuggest.this, "Impossible de récupérer vos coordonnées", Toast.LENGTH_SHORT).show();
            }
        } else {

            Log.d("GPS", "permission refusée demande autorisation");
            Toast.makeText(MapsActivitySuggest.this, "permission refusée", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(MapsActivitySuggest.this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }

    }

    private CircleOptions drawCircle(LatLng point){

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(20000);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x30ff0000);

        // Border width of the circle
        circleOptions.strokeWidth(2);
        return circleOptions;

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        if (location != null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MapsActivitySuggest.this,
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }


            mMap.setMyLocationEnabled(true);
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
            CircleOptions circlePosition = drawCircle(position);
            mMap.addCircle(circlePosition);

            LocationAvailability locationAvailability =
                    LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
            if (null != locationAvailability && locationAvailability.isLocationAvailable()) {

                location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
                 position = new LatLng(location.getLatitude(), location.getLongitude());
                circlePosition = drawCircle(position);
                mMap.addCircle(circlePosition);

                if (location != null) {
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());

                     appliuser = new ApliUser (emailname,new CoordinatesDouble(new double[]{location.getLatitude(),location.getLongitude()}));
                    Session.appuser = appliuser;
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
                }
            }

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            RecupMonument AlistMonu=new RecupMonument();

            ArrayList<MarkerOptions> marqueurmonus = listMarqueurMonu(AlistMonu.getAlistMonu());
            afficheListMarker(list_monument,keepMarker(marqueurmonus,circlePosition),placeMarker(marqueurmonus,circlePosition));

        }
    }
    @Override
    public void onBackPressed() {
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_room) {
            Intent intent = new Intent(MapsActivitySuggest.this, MapsActivitySuggest.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout) {
            session.logoutUser();

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MapsActivitySuggest.this, ProfilActivity.class);
            startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mGoogleApiClient.disconnect();
        }
    }


    public ArrayList<MarkerOptions> listMarqueurMonu(ArrayList<ApliMonument> listlatlon){
        int i=0;

        ArrayList<MarkerOptions> marMonu = new ArrayList<MarkerOptions>();
        for(ApliMonument lalo : listlatlon  ){
            MarkerOptions markerop = new MarkerOptions().position(lalo.getGeoloc()).title(lalo.getName());
            marMonu.add(markerop);
            i++;
        }
        return marMonu;
    }


       public ArrayList<ApliMonument> placeMarker(ArrayList<MarkerOptions> markersO, CircleOptions circleP) {
           ArrayList<ApliMonument> markerselect = new ArrayList<ApliMonument>();
           for (int i = 0; i <= markersO.size() - 1; i++) {
               ApliMonument monu = new ApliMonument();
               monu.setGeoloc(markersO.get(i).getPosition());
               float[] distance = new float[2];

               monu.setDistance(monu.calculdistance(monu.getGeoloc(), circleP.getCenter().latitude, circleP.getCenter().longitude));
               Location.distanceBetween(markersO.get(i).getPosition().latitude, markersO.get(i).getPosition().longitude,
                       circleP.getCenter().latitude, circleP.getCenter().longitude, distance);

               if (monu.getDistance()[0] < circleP.getRadius()) {
                   mMap.addMarker(markersO.get(i));

                    monu.setName(markersO.get(i).getTitle());
                   markerselect.add(monu);


               }
           }
           return markerselect;
       }


    public ArrayList<String> keepMarker(ArrayList<MarkerOptions> markersO,CircleOptions circleP) {
        ArrayList<String> markerselect = new ArrayList<String>();
        for (int i = 0; i <= markersO.size() - 1; i++) {
            float[] distance = new float[2];
            Location.distanceBetween(markersO.get(i).getPosition().latitude, markersO.get(i).getPosition().longitude,
                    circleP.getCenter().latitude, circleP.getCenter().longitude, distance);

            if (distance[0] < circleP.getRadius()) {
                markerselect.add(markersO.get(i).getTitle());
            }
        }
        return markerselect;
    }

    public void afficheListMarker(ListView listV, final ArrayList<String> keepMarker,final ArrayList<ApliMonument> keepMarkerApliMonument){
        ArrayAdapter<String> listadaptater;
        listadaptater = new ArrayAdapter<String>(MapsActivitySuggest.this, android.R.layout.simple_list_item_1, keepMarker);
        listV.setAdapter(listadaptater);


        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                preferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                editor = preferences.edit();
                for (int i = 0; i < keepMarker.size(); i++) {
                    if (position == i) {
                        //Toast.makeText(MapsActivity.this, keepMarker.get(i).toString(), Toast.LENGTH_SHORT).show();
                        DecimalFormat df = new DecimalFormat("#");
                        String elt = ""+df.format(keepMarkerApliMonument.get(i).getDistance()[0]);
                        keepMarkerApliMonument.get(i).setId(i);
                        String idMonument = String.valueOf(keepMarkerApliMonument.get(i).getId());
                        String monument_name = keepMarkerApliMonument.get(i).getName();

                        editor.putString(KEY_DIST_MONUMENT, elt);
                        editor.putString(KEY_NAME_MONUMENT, monument_name);


                        editor.putString(KEY_ID_MONUMENT, idMonument);


                        Intent intent = new Intent(MapsActivitySuggest.this, MonumentActivity.class);
                        startActivity(intent);

                    }
                }
                editor.commit();
            }
        });

    }

}
