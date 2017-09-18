package com.nicebin;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NearestBin extends AppCompatActivity {

    List<String> lat, lng, name, full;
    List<Double> diff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_bin);
        lat = new ArrayList<>();
        lng = new ArrayList<>();
        name = new ArrayList<>();
        full = new ArrayList<>();
        diff = new ArrayList<>();

        if (isNetworkAvailable(this)) {

            new GetAllBins(this).execute();
        }else {
            Toast.makeText(this, "Please Connect with Internet", Toast.LENGTH_SHORT).show();
        }

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


                        name.add(c.getString("name"));
                        lat.add(c.getString("latitude"));
                        lng.add(c.getString("longitude"));
                        full.add(c.getString("full"));

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
            pDialog = ProgressDialog.show(NearestBin.this,
                    null, "Please Wait", true, false);

        }

        @Override
        protected void onPostExecute(String result) {

            pDialog.dismiss();

            for (int i = 0; i < lat.size(); i++){
                diff.add(Double.valueOf(String.valueOf(distance(45.064595, 7.659477, Double.parseDouble(lat.get(i)), Double.parseDouble(lng.get(i))))));
            }

            /*Collections.sort(diff);
            Collections.reverse(diff);
            Collections.sort(full);
            Collections.reverse(full);
            Collections.sort(name);
            Collections.reverse(name);*/


            ((TextView) findViewById(R.id.realSuggestion)).setText(suggest());

            /*if (Integer.parseInt(full.get()) < 80) {
                ((TextView) findViewById(R.id.realSuggestion)).setText(name.get(8));
            }else if (Integer.parseInt(full.get(7)) < 80) {
                ((TextView) findViewById(R.id.realSuggestion)).setText(name.get(7));
            }else
                ((TextView)findViewById(R.id.realSuggestion)).setText("No Bin Available");*/
        }


    }


    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;

        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public String suggest() {

        int smallest = Integer.parseInt(full.get(0));
        String binName = name.get(0);

        for (int i=1;i<full.size();i++) {
            if(Integer.parseInt(full.get(i)) < smallest) {
                smallest = Integer.parseInt(full.get(i));
                binName = name.get(i);
            }
        }

        return smallest + "% Full, " + binName;
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
