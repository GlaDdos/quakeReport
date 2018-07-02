package com.example.kamil.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Create a fake list of earthquake locations.
            final ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();

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

}
