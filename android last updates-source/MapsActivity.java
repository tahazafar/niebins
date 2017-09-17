package com.nicebin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        group[0] = new ArrayList<>();
        group[1] = new ArrayList<>();
        group[2] = new ArrayList<>();
        group[3] = new ArrayList<>();
        allBinLocations = new ArrayList<LatLng>();

        if (isNetworkAvailable(this)) {

            new GetAllBins(this).execute();
        }else {
            Toast.makeText(this, "Please Connect with Internet", Toast.LENGTH_SHORT).show();
        }

        /*DB = new DatabaseHandler(this);

        group = DB.getAllLocations();
*/


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

    public class GetAllBins extends AsyncTask<String, Void, String> {
        Context context;
        ProgressDialog pDialog;
        GetAllBins(Context cx){
            context = cx;
        }
        @Override
        protected String doInBackground(String... params) {
            String binUrl = "http://nicebin.000webhostapp.com/getAllBins.php";
            try {

                String result = new HttpHandlerPost().Request(binUrl, "");

                if (result != null) {
                    JSONObject jsonObj = null;

                    jsonObj = new JSONObject(result);
                    JSONArray binsArray = null;
                    binsArray = jsonObj.getJSONArray("info");

                    // looping through All Contacts
                    for (int i = 0; i < binsArray.length(); i++) {
                        final JSONObject c = binsArray.getJSONObject(i);


                        group[0].add(c.getString("name"));
                        group[1].add(c.getString("latitude"));
                        group[2].add(c.getString("longitude"));
                        group[3].add(c.getString("full"));

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = ProgressDialog.show(MapsActivity.this,
                    null, "Please Wait", true, false);

        }

        @Override
        protected void onPostExecute(String result) {


            pDialog.dismiss();

            for (int i=0; i<group[0].size(); i++ ){
                LatLng temp = new LatLng(Double.parseDouble(group[1].get(i)), Double.parseDouble(group[2].get(i)));
                allBinLocations.add(temp);
            }

            Log.i("Data", group[0].size()+"");

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(MapsActivity.this);



            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,
                    1, mLocationListener);


        }


    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
