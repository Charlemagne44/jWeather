package com.charlemagne44.jweather;

import com.charlemagne44.jweather.geocodingapi.Geocode;

public class App {
    public static void main(String[] args) {
        Geocode geocoder = new Geocode();
        System.out.println(geocoder.getLatLong("New York").latitude + " " + geocoder.getLatLong("New York").longitude);
    }
}
