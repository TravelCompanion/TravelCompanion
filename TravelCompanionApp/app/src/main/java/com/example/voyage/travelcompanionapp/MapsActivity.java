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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.voyage.api.api.ia.IAManager;
import com.example.voyage.travelcompanionapp.callwebservice.ConnexionInternet;
import com.example.voyage.travelcompanionapp.model.ApliMonument;
import com.example.voyage.travelcompanionapp.model.ApliUser;
import com.example.voyage.travelcompanionapp.callwebservice.RecupMonument;
import com.google.android.gms.ads.InterstitialAd;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
//import com.example.voyage.api.externalData.VirtualUser;
import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

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
    public static final String KEY_DESCRIP_MONUMENT = "description";

    ApliUser appliuser = new ApliUser();
    private static final String PREF_NAME = "PrefDistance";

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    String emailname="";
    private DrawerLayout drawer;
    Toolbar toolbar;
    Switch switcha;
    CircleOptions circlePosition;



    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_map);
        list_monument = (ListView) findViewById(R.id.list_monuments);
        session = Session.getSession(getApplicationContext());
        session.checkLogin();

        HashMap<String, String> userSession = session.getUserDetails();
        // emailname
        emailname = userSession.get(session.KEY_EMAIL);

        Toast.makeText(getApplicationContext(), "vous etes connecté: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        switcha = (Switch)findViewById(R.id.switchAB);
        switcha.setText(R.string.title_switch_proximité);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void getCoord() {
        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

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
                                appliuser = new ApliUser (session.appuser.getId(),new CoordinatesDouble(new double[]{location.getLatitude(),location.getLongitude()}),session.appuser.getPreferences());
                                session.appuser = appliuser;
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
                Toast.makeText(MapsActivity.this, "Impossible de récupérer vos coordonnées", Toast.LENGTH_SHORT).show();
            }
        } else {

            Log.d("GPS", "permission refusée demande autorisation");
            Toast.makeText(MapsActivity.this, "permission refusée", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(MapsActivity.this,
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
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }

            mMap.setMyLocationEnabled(true);
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
            circlePosition = drawCircle(position);
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

                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
                }
            }

            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            switcha.setChecked(false);
            RecupMonument AlistMonu=new RecupMonument();
            afficheListMarker(list_monument,AlistMonu.getWebServiceMonument(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude())),circlePosition,mMap);
            /*String currentafficheLocation=String.valueOf(location.getLatitude())+"/"+String.valueOf(location.getLongitude());
            Toast.makeText(getApplicationContext(),currentafficheLocation , Toast.LENGTH_LONG).show();*/

            switcha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        RecupMonument AlistMonu=new RecupMonument();
                        afficheListMarkerSuggestion(list_monument,MonumentActivity.requestSuggest(session.appuser,AlistMonu.getWebServiceMonument(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()))),circlePosition,mMap);
                        switcha.setText(R.string.title_switch_suggestion);

                    }
                    if (isChecked==false){
                        RecupMonument AlistMonu=new RecupMonument();
                        afficheListMarker(list_monument,AlistMonu.getWebServiceMonument(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude())),circlePosition,mMap);
                        switcha.setText(R.string.title_switch_proximité);

                    }
                }
            });
            /*RecupMonument AlistMonu=new RecupMonument();
            if(ConnexionInternet.isConnectedInternet(MapsActivity.this)){
                //marqueurmonus = listMarqueurMonu(AlistMonu.getWebServiceMonument());
                ArrayList<ApliMonument> monu1;
                ArrayList<ApliMonument> monu2;
                afficheListMarker(list_monument,AlistMonu.getWebServiceMonument(),circlePosition,mMap);
                //Suggestion
                monu1=MonumentActivity.requestSuggest(Session.appuser,AlistMonu.getWebServiceMonument());
                Log.d("succes",monu1.toString());
            }
            else{
                afficheListMarker(list_monument,AlistMonu.getAlistMonu(),circlePosition,mMap);
            }*/

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
        /*getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.myswitch);
        menuItem.setActionView(R.layout.switch_button_layout);
        switcha = (Switch)menu.findItem(R.id.myswitch).getActionView().findViewById(R.id.switchAB);
        switcha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "bouton check " , Toast.LENGTH_LONG).show();

                }
                if(isChecked==false){
                    Toast.makeText(getApplicationContext(), "bouton uncheck " , Toast.LENGTH_LONG).show();

                }
            }
        });*/


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
            Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_logout) {
            session.logoutUser();

        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(MapsActivity.this, ProfilActivity.class);
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


    public void placeMarker(ArrayList<ApliMonument> listlatlon, GoogleMap googleMap) {
            googleMap.clear();
            for(ApliMonument lalo: listlatlon) {
                MarkerOptions markerop = new MarkerOptions().position(lalo.getGeoloc()).title(lalo.getName());
                googleMap.addMarker(markerop);
            }

    }

    public void placeMarkerLine(ArrayList<ApliMonument> listlatlon, GoogleMap googleMap, LatLng currentl) {
        googleMap.clear();
        ArrayList<LatLng> points= new ArrayList<LatLng>();
        PolylineOptions polylineOptions = new PolylineOptions();

        polylineOptions.color(Color.argb(Color.alpha(Color.BLUE), Color.BLUE,Color.GREEN,Color.GRAY));
        polylineOptions.width(2);
        points.add(currentl);
        for(ApliMonument lalo: listlatlon) {
            MarkerOptions markerop = new MarkerOptions().position(lalo.getGeoloc()).title(lalo.getName());
            points.add(lalo.getGeoloc());
            googleMap.addMarker(markerop);
        }
        polylineOptions.addAll(points);
        googleMap.addPolyline(polylineOptions);

    }




        public ArrayList<String> nameFilterMonu(ArrayList<ApliMonument>listmonu){
        ArrayList<String> markerselect = new ArrayList<String>();
        for(ApliMonument monument : listmonu  ){

                markerselect.add(monument.getName());

        }
        return markerselect;
    }

    public ArrayList<ApliMonument> filterMonu(ArrayList<ApliMonument>listmonu,CircleOptions circleP){
        ArrayList<ApliMonument> markerselect = new ArrayList<ApliMonument>();
        for(ApliMonument monument : listmonu  ){
            monument.setDistance(monument.calculdistance(monument.getGeoloc(), circleP.getCenter().latitude, circleP.getCenter().longitude));


            if (monument.getDistance()[0] < circleP.getRadius()) {
                markerselect.add(monument);

            }

        }
        Collections.sort(markerselect);
        return markerselect;
    }

    public void afficheListMarker(ListView listV, final ArrayList<ApliMonument> keepMarkerApliMonument, final CircleOptions circleP, final GoogleMap googleMap){
        ArrayAdapter<String> listadaptater;
        final ArrayList<ApliMonument>filtermonu=filterMonu(keepMarkerApliMonument,circleP);
        ArrayList<String> namemonufilter=nameFilterMonu(filtermonu);

        listadaptater = new ArrayAdapter<String>(MapsActivity.this, android.R.layout.simple_list_item_1, namemonufilter);
        listV.setAdapter(listadaptater);
        placeMarker(filtermonu,googleMap);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                preferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                editor = preferences.edit();
                for (int i = 0; i < filtermonu.size(); i++) {
                    filtermonu.get(i).setDistance(filtermonu.get(i).calculdistance(filtermonu.get(i).getGeoloc(), circleP.getCenter().latitude, circleP.getCenter().longitude));

                    if (position == i) {
                        DecimalFormat df = new DecimalFormat("#");
                        String elt = ""+df.format(filtermonu.get(i).getDistance()[0]);
                        String idMonument = String.valueOf(filtermonu.get(i).getId());
                        String monument_name = filtermonu.get(i).getName();

                        editor.putString(KEY_DIST_MONUMENT, elt);
                        editor.putString(KEY_NAME_MONUMENT, monument_name);
                        editor.putString(KEY_DESCRIP_MONUMENT,String.valueOf(filtermonu.get(i).getDescription()));

                        editor.putString(KEY_ID_MONUMENT, idMonument);
                        Log.d("Id monument",idMonument);
                        Intent intent = new Intent(MapsActivity.this, MonumentActivity.class);
                        startActivity(intent);

                    }
                }
                editor.commit();
            }
        });

    }

    public void afficheListMarkerSuggestion(ListView listV, final ArrayList<ApliMonument> keepMarkerApliMonument, final CircleOptions circleP, final GoogleMap googleMap){
        ArrayAdapter<String> listadaptater;
        ArrayList<String> namemonu=nameFilterMonu(keepMarkerApliMonument);

        listadaptater = new ArrayAdapter<String>(MapsActivity.this, android.R.layout.simple_list_item_1, namemonu);
        listV.setAdapter(listadaptater);
        LatLng currentlocation=new LatLng(location.getLatitude(),location.getLongitude());
        placeMarker(keepMarkerApliMonument,googleMap);

        listV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                preferences = getSharedPreferences(PREF_NAME, PRIVATE_MODE);
                editor = preferences.edit();
                for (int i = 0; i < keepMarkerApliMonument.size(); i++) {
                    keepMarkerApliMonument.get(i).setDistance(keepMarkerApliMonument.get(i).calculdistance(keepMarkerApliMonument.get(i).getGeoloc(), circleP.getCenter().latitude, circleP.getCenter().longitude));

                    if (position == i) {
                        DecimalFormat df = new DecimalFormat("#");
                        String elt = ""+df.format(keepMarkerApliMonument.get(i).getDistance()[0]);
                        String idMonument = String.valueOf(keepMarkerApliMonument.get(i).getId());
                        String monument_name = keepMarkerApliMonument.get(i).getName();

                        editor.putString(KEY_DIST_MONUMENT, elt);
                        editor.putString(KEY_NAME_MONUMENT, monument_name);
                        editor.putString(KEY_DESCRIP_MONUMENT,String.valueOf(keepMarkerApliMonument.get(i).getDescription()));

                        editor.putString(KEY_ID_MONUMENT, idMonument);
                        //A modifier pour apprentissage
                        IAManager.selectPlace(position,IAManager.results);
                        //IAManager.shortLearn(session.USER_CONVERTION_APLI.convertFrom(Session.appuser),session.PLACE_CONVERTION_APLI.convertFrom(keepMarkerApliMonument.get(i)));

                        Log.d("Id monument",idMonument);
                        Intent intent = new Intent(MapsActivity.this, MonumentActivity.class);
                        startActivity(intent);

                    }
                }
                editor.commit();
            }
        });

    }

}
