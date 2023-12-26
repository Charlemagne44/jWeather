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

import java.net.URL;

public class Geocode {
    private static String getLocationJSONResponse(String location) {
        StringBuilder response = new StringBuilder();
        try {
            // Example: Geocode by city name
            String nominatimUrl = "https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(location, "UTF-8") + "&format=json";

            // Create a URL object
            URL url = new URL(nominatimUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Set the User-Agent header
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Read the response from the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Close the connection
            connection.disconnect();
            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public static List<Double> getLatLong(String cityName) {
        String jsonResponse = getLocationJSONResponse(cityName);
        List<Double> coords = new ArrayList<>();
        try {
            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(jsonResponse);

            if (jsonArray.length() > 0) {
                JSONObject firstResult = jsonArray.getJSONObject(0);
                double latitude = firstResult.getDouble("lat");
                double longitude = firstResult.getDouble("lon");
                coords.add(latitude);
                coords.add(longitude);
            } else {
                System.out.println("No results found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return coords;
    }
}
