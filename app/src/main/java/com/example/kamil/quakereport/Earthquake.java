package com.example.kamil.quakereport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private double magnitude;
    private String location;
    private Date date;

    public Earthquake(double magnitude, String location, Date date) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
    }

    /* Constructor taking string date in format day.month.year*/
    public Earthquake(double magnitude, String location, String sDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            this.date = dateFormat.parse(sDate);
        } catch (ParseException e) {

            this.date = null;
            e.printStackTrace();
        }
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    //getter for date formatted to string
    public String getDateString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");
        return dateFormat.format(date);
    }
}
