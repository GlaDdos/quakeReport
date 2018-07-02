package com.example.kamil.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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

        TextView tvTime = (TextView) listItemView.findViewById(R.id.tvTime);
        TextView tvDate = (TextView) listItemView.findViewById(R.id.tvDate);
        String[] dateTime = quake.getDateString().split(" ");

        tvDate.setText(dateTime[0]);
        tvTime.setText(dateTime[1]);


        //splitting location string to primary location and offset
        String[] locationArray = quake.getLocation().split(" of ");

        TextView tvLocationOffset = (TextView) listItemView.findViewById(R.id.tvOffsetLocation);
        TextView tvLocation = (TextView) listItemView.findViewById(R.id.tvLocation);

        if (locationArray.length > 1) {
            tvLocationOffset.setText(locationArray[0] + " of");
            tvLocation.setText(locationArray[1]);
        } else {
            tvLocationOffset.setText("Near the ");
            tvLocation.setText(locationArray[0]);
        }

        TextView tvMagnitude = (TextView) listItemView.findViewById(R.id.tvMagnitude);
        tvMagnitude.setText(String.valueOf(quake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) tvMagnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(quake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {

        switch ((int) Math.ceil(magnitude)) {
            case 0:
            case 1:
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);

            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);

            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);

            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);

            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);

            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);

            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);

            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);

            case 10:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);

            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }
    }
}
