package com.example.kamil.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    public static final String LOG_TAG = EarthquakeLoader.class.getSimpleName();
    private URL url;

    EarthquakeLoader(Context context, String url) {
        super(context);

        this.url = createUrl(url);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        try {
            String jsonData = makeHttpRequest(url);
            earthquakes = extractEarthquakes(jsonData);

        } catch (IOException e) {
            Log.d(LOG_TAG, "Problem retrieving earthquake json from API." + e);

        }

        return earthquakes;
    }


    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if(url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(15000);
            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);


            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);

        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();

            }

            if(inputStream != null) {
                inputStream.close();

            }
        }

        return jsonResponse;
    }


    private static URL createUrl(String sUrl) {
        URL url = null;

        try {
            url = new URL(sUrl);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error createing URL", e);
        }

        return url;
    }

    private static String readFromStream (InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if(inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line = reader.readLine();

            while(line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }

        return output.toString();
    }

    public static ArrayList<Earthquake> extractEarthquakes(String earthquakeJSON) {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject quakeJson = new JSONObject(earthquakeJSON);
            JSONArray quakeArray = quakeJson.getJSONArray("features");

            for (int i = 0; i < quakeArray.length(); i++) {
                JSONObject currentQuake = quakeArray.getJSONObject(i);
                JSONObject currentProperties = currentQuake.getJSONObject("properties");

                earthquakes.add(new Earthquake(
                        currentProperties.getDouble("mag"),
                        currentProperties.getString("place"),
                        new Date(currentProperties.getLong("time")),
                        currentProperties.getString("url")
                ));


            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }
}
