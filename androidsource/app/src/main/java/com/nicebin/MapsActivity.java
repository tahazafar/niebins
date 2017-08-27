package com.nicebin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    LocationManager mLocationManager;
    DatabaseHandler DB;

    ArrayList<String>[] group = new ArrayList[4];
    ArrayList<LatLng> allBinLocations;

    Marker CurrentLocation;
    GoogleMap n;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Alarm for
        // Remainder to user
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        start();


        group[0] = new ArrayList<>();group[1] = new ArrayList<>();group[2] = new ArrayList<>();group[3] = new ArrayList<>();

        DB = new DatabaseHandler(this);

        group = DB.getAllLocations();

        allBinLocations = new ArrayList<LatLng>();


        for (int i=0; i<group[0].size(); i++ ){
            LatLng temp = new LatLng(Double.parseDouble(group[1].get(i)), Double.parseDouble(group[2].get(i)));
            allBinLocations.add(temp);
        }

        Log.i("Data", group[0].size()+"");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,
                1, mLocationListener);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        n = googleMap;
        for (int i=0; i<allBinLocations.size(); i++){

            int per = Integer.parseInt(group[3].get(i));
            if (per>=0 & per <=40){
                LatLng italy = allBinLocations.get(i);
                googleMap.addMarker(new MarkerOptions().position(italy)
                        .title(group[0].get(i))).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.green));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(italy,15));
            }else if (per>=41 & per <=80){
                LatLng italy = allBinLocations.get(i);
                googleMap.addMarker(new MarkerOptions().position(italy)
                        .title(group[0].get(i))).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.yellow));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(italy,15));

            }else{
                LatLng italy = allBinLocations.get(i);
                googleMap.addMarker(new MarkerOptions().position(italy)
                        .title(group[0].get(i))).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.red));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(italy,15));

            }

/*
            Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
/*
            if (n!=null){

                if (CurrentLocation != null)
                    CurrentLocation.remove();

                LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());

                CurrentLocation = n.addMarker(new MarkerOptions().position(mylocation).title("My location"));

                CurrentLocation.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.current));
            }*/

        }
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            if (CurrentLocation != null)
            CurrentLocation.remove();

            LatLng mylocation = new LatLng(location.getLatitude(), location.getLongitude());

            CurrentLocation = n.addMarker(new MarkerOptions().position(mylocation).title("My location"));

            CurrentLocation.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.current));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 21600*1000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
       // Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }





}
