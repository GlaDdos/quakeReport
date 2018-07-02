package com.example.kamil.quakereport;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Earthquake {

    private double magnitude;
    private String location;
    private Date date;

    private String url;

    public Earthquake(double magnitude, String location, Date date, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.date = date;
        this.url = url;
    }

    /* Constructor taking string date in format day.month.year*/
    public Earthquake(double magnitude, String location, String sDate, String url) {
        this.location = location;
        this.magnitude = magnitude;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy hh:mm");
        return dateFormat.format(date);
    }

    public String getUrl() {
        return url;
    }
}
