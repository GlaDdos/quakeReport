package com.example.kamil.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> quake) {
        super(context, 0, quake);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item, parent, false);
        }

        Earthquake quake = getItem(position);

        TextView tvMagnitude = (TextView) listItemView.findViewById(R.id.tvMagnitude);
        tvMagnitude.setText(String.valueOf(quake.getMagnitude()));

        TextView tvLocation = (TextView) listItemView.findViewById(R.id.tvLocation);
        tvLocation.setText(quake.getLocation());

        TextView tvDate = (TextView) listItemView.findViewById(R.id.tvDate);
        tvDate.setText(quake.getDateString());

        return listItemView;
    }
}
