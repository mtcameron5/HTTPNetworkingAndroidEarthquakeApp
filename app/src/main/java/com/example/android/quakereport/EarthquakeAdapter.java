package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.graphics.drawable.GradientDrawable;

import static java.lang.Math.floor;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private static final String LOCATION_SEPARATOR =" of ";

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }


        Earthquake currentEarthquake = getItem(position);


        // Set proper background color on magnitude circle
        TextView earthquakeMagnitude = (TextView) listItemView.findViewById(R.id.earthquake_magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) earthquakeMagnitude.getBackground();

        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        magnitudeCircle.setColor(magnitudeColor);

        double magnitude = currentEarthquake.getMagnitude();
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(magnitude);

        earthquakeMagnitude.setText(output);

        long timeInMilliseconds = Long.parseLong(currentEarthquake.getDate());
        Date dateObject = new Date(timeInMilliseconds);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormat.format(dateObject);


        TextView earthquakeDate = (TextView) listItemView.findViewById(R.id.earthquake_date);
        earthquakeDate.setText(dateToDisplay);

        TextView earthquakeTime = (TextView) listItemView.findViewById(R.id.earthquake_time);
        earthquakeTime.setText(timeToDisplay);

        String primaryLocation;
        String locationOffset;
        String originalLocation = currentEarthquake.getLocation();
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        TextView earthquakeLocation = (TextView) listItemView.findViewById(R.id.earthquake_location);
        earthquakeLocation.setText(primaryLocation);

        TextView earthquakeOffset = (TextView) listItemView.findViewById(R.id.earthquake_coordinates);
        earthquakeOffset.setText(locationOffset);






        return listItemView;


    }


    private int getMagnitudeColor(double magnitude) {
        double flooredMagnitude = floor(magnitude);
        int intMagnitude = (int) flooredMagnitude;
        switch(intMagnitude) {
            case 0:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);


        }

    }
}
