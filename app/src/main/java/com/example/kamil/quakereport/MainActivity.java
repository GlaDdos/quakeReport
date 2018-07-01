package com.example.kamil.quakereport;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            ArrayList<Earthquake> earthquakes = new ArrayList<>();
            earthquakes.add(new Earthquake(4.6, "San Diego", "12.01.2012"));
            earthquakes.add(new Earthquake(4.6, "London", "12.01.1874"));
            earthquakes.add(new Earthquake(4.6, "Sri Lanka", "12.01.1203"));
            earthquakes.add(new Earthquake(4.6, "this town", "12.01.1393"));
            earthquakes.add(new Earthquake(4.6, "that town", "12.01.1939"));
            earthquakes.add(new Earthquake(4.6, "an island", "12.01.2013"));
            earthquakes.add(new Earthquake(4.6, "under the sea", "12.01.2014"));
            earthquakes.add(new Earthquake(4.6, "ocean", "12.01.2213"));

            ListView earthquakeListView = (ListView) findViewById(R.id.list);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, earthquakes);

            earthquakeListView.setAdapter(adapter);
        }
}
