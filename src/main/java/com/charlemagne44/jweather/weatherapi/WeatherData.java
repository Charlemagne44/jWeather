package com.charlemagne44.jweather.weatherapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherData {
    long latitude;
    long longitude;

    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public WeatherData(long latitude, long longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getWeatherData() {
        String baseUrl = "https://api.open-meteo.com/v1/forecast?latitude=%d&longitude=%d&current=temperature_2m,wind_speed_10m";
        String callUrl = String.format(baseUrl, this.getLatitude(), this.getLongitude());
        
        URL url;
        HttpURLConnection connection;
        // int responseCode;
        String line;
        StringBuilder response;
        try {
            // Construct and make the request
            url = new URL(callUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // responseCode = connection.getResponseCode();

            // Read the response from the input stream
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();

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

        return "";
    }
}
