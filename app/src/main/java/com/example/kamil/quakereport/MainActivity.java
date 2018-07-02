package com.example.kamil.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EarthQuakeAsyncTask task = new EarthQuakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

    }

    private void updateUI(ArrayList<Earthquake> alEarthquakes) {

        final ArrayList<Earthquake> earthquakes = alEarthquakes;

        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(this, earthquakes);

        earthquakeListView.setAdapter(earthquakeAdapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = earthquakes.get(position).getUrl();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));

                startActivity(intent);
            }
        });

    }

    private class EarthQuakeAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if(urls.length < 1 || urls[0] == null) {
                return null;
            }

            ArrayList<Earthquake> result = QueryUtils.fetchEarthquakeData(urls[0]);

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            if(earthquakes == null) {
                return;
            }

            updateUI(earthquakes);
        }
    }

}
