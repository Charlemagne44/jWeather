package com.charlemagne44.jweather.geocodingapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import com.charlemagne44.jweather.apirequest.Apirequest;

import java.net.URL;

public class Geocode {

    public class LatLong {
        public double latitude;
        public double longitude;

        public LatLong(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }

    private static String getLocationJSONResponse(String location) {
        try {
            // Example: Geocode by city name
            String nominatimUrl = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(location, "UTF-8")
                    + "&format=json";
            return Apirequest.request(nominatimUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LatLong getLatLong(String cityName) {
        String stringResponse = getLocationJSONResponse(cityName);
        try {
            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(stringResponse);

            if (jsonArray.length() > 0) {
                JSONObject firstResult = jsonArray.getJSONObject(0);
                double latitude = firstResult.getDouble("lat");
                double longitude = firstResult.getDouble("lon");
                return this.new LatLong(latitude, longitude);
            } else {
                System.out.println("No results found");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
