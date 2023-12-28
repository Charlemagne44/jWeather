package com.charlemagne44.jweather.weatherapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {
    int hourlyRange = 48; // gets the next 48 hours of weather data

    double latitude;
    double longitude;

    Map<String, String> currentWeather;
    Map<String, String> currentWeatherUnits;
    Map<String, String> current; // key -> Value + Unit

    Map<String, String> hourlyWeather;
    Map<String, String> hourlyWeatherUnits;
    Map<String, String> hourly; // key -> Value + Unit

    Map<String, String> dailyWeather;
    Map<String, String> dailyWeatherUnits;
    Map<String, String> daily; // key -> Value + Unit

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Map<String, String> getCurrent() {
        return current;
    }

    public Map<String, String> getHourly() {
        return hourly;
    }

    public Map<String, String> getDaily() {
        return daily;
    }

    public WeatherData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /* Combine the weather data + units into a Map<String, List<String>> */
    private Map<String, String> combineWeatherData(Map<String, String> weatherData,
            Map<String, String> weatherUnits) {
        // the Keys between the data and the units are the same in the JSON response
        Map<String, String> combinedMap = new HashMap<>();
        for (Map.Entry<String, String> entry : weatherData.entrySet()) {
            String key = entry.getKey();
            String data = entry.getValue();
            String unit = weatherUnits.get(key);
            combinedMap.put(key, data + unit);
        }
        return combinedMap;
    }

    public void getCurrentWeatherData() {
        String currentFields = "&current=temperature_2m,relative_humidity_2m,apparent_temperature,"
                + "is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,"
                + "surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m";
        String currentWeatherJSON = getWeatherJSON(currentFields);
        this.currentWeather = parseCurrentWeatherJSON(currentWeatherJSON, "current");
        this.currentWeatherUnits = parseCurrentWeatherJSON(currentWeatherJSON, "current_units");
        this.current = combineWeatherData(currentWeather, currentWeatherUnits);
    }

    public void getHourlyWeatherData() {
        String hourlyFields = "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,"
                + "precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weather_code,"
                + "pressure_msl,surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,"
                + "visibility,evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit,wind_speed_10m,"
                + "wind_speed_80m,wind_speed_120m,wind_speed_180m,wind_direction_10m,wind_direction_80m,"
                + "wind_direction_120m,wind_direction_180m,wind_gusts_10m,temperature_80m,temperature_120m,"
                + "temperature_180m,soil_temperature_0cm,soil_temperature_6cm,soil_temperature_18cm,soil_temperature_54cm,"
                + "soil_moisture_0_to_1cm,soil_moisture_1_to_3cm,soil_moisture_3_to_9cm,soil_moisture_9_to_27cm,"
                + "soil_moisture_27_to_81cm&forecast_days=" + String.valueOf(this.hourlyRange);
        String hourlyWeatherJSON = getWeatherJSON(hourlyFields);
        this.hourlyWeather = parseCurrentWeatherJSON(hourlyWeatherJSON, "hourly");
        this.hourlyWeatherUnits = parseCurrentWeatherJSON(hourlyWeatherJSON, "hourly_units");
        this.hourly = combineWeatherData(hourlyWeather, hourlyWeatherUnits);
    }

    public void getDailyWeatherData() {
        String dailyFields = "&daily=weather_code,temperature_2m_max,temperature_2m_min,apparent_temperature_max,"
                + "apparent_temperature_min,sunrise,sunset,uv_index_max,uv_index_clear_sky_max,precipitation_sum,"
                + "rain_sum,showers_sum,snowfall_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,"
                + "wind_gusts_10m_max,wind_direction_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration";
        String dailyWeatherJSON = getWeatherJSON(dailyFields);
        this.dailyWeather = parseCurrentWeatherJSON(dailyWeatherJSON, "daily");
        this.dailyWeatherUnits = parseCurrentWeatherJSON(dailyWeatherJSON, "daily_units");
        this.hourly = combineWeatherData(dailyWeather, dailyWeatherUnits);
    }

    private Map<String, String> parseCurrentWeatherJSON(String jsonResponse, String responseKey) {
        Map<String, String> values = new HashMap<>();
        try {
            JSONObject json = new JSONObject(jsonResponse);
            JSONObject responseCategory = json.getJSONObject(responseKey);

            Iterator<String> keys = responseCategory.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = responseCategory.get(key);
                String stringValue = value.toString();
                values.put(key, stringValue);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return values;
    }

    private String getWeatherJSON(String requestFields) {
        String baseUrl = "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f";
        String callUrl = String.format(baseUrl, this.getLatitude(), this.getLongitude()) + requestFields;

        URL url;
        HttpURLConnection connection;
        String line;
        StringBuilder response = new StringBuilder();
        try {
            // Construct and make the request
            url = new URL(callUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

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
