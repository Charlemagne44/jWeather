package com.charlemagne44.jweather.weatherapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {
    double latitude;
    double longitude;
    String currentTime;
    double currentTemp, currentHumidty;

    public String getCurrentTime() {
        return currentTime;
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public double getCurrentHumidty() {
        return currentHumidty;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public WeatherData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        parseWeatherJSON(getWeatherJSON());
    }

    private void parseWeatherJSON(String jsonResponse) {
        try {// Parse the JSON response
            JSONObject json = new JSONObject(jsonResponse);

            // Extract current information
            JSONObject current = json.getJSONObject("current");
            this.currentTime = current.getString("time");
            this.currentTemp = current.getDouble("temperature_2m");
            this.currentHumidty = current.getDouble("relative_humidity_2m");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getWeatherJSON() {
        String baseUrl = "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,wind_speed_10m,relative_humidity_2m";
        String callUrl = String.format(baseUrl, this.getLatitude(), this.getLongitude());

        URL url;
        HttpURLConnection connection;
        // int responseCode;
        String line;
        StringBuilder response = new StringBuilder();
        try {
            // Construct and make the request
            url = new URL(callUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // responseCode = connection.getResponseCode();

            // Read the response from the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            // Close the connection and return
            connection.disconnect();
            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
