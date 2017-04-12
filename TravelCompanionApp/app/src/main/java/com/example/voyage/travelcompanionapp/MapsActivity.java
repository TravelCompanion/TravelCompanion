package com.example.voyage.travelcompanionapp;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.voyage.travelcompanionapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private Location location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        getCoord();



       /* if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 150, new LocationListener() {

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
                    Log.d("GPS", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
                }
            });

        }*/




    }
    public void getCoord(){
        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(MapsActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MapsActivity.this, "permission accordée", Toast.LENGTH_SHORT).show();

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria critere = new Criteria();
            critere.setAccuracy(Criteria.ACCURACY_FINE);
            ArrayList<LocationProvider> providers = new ArrayList<LocationProvider>();
            List<String> names = locationManager.getProviders(critere, true);
            for (String name : names) {
                providers.add(locationManager.getProvider(name));

            }

            if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, new LocationListener() {
                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        Log.d("Provider Enabled", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
                        Toast.makeText(MapsActivity.this, "Provider Enabled", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        Log.d("Provider Disabled", "Provider Disabled");

                    }

                    @Override
                    public void onLocationChanged(Location location) {

                            Log.d("GPS Activé", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());

                    }
                });

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
        }
        else{

            Log.d("GPS", "permission refusée demande autorisation");
            Toast.makeText(MapsActivity.this, "permission refusée", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }

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
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
