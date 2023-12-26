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
    double latitude;
    double longitude;

    /*
     *         String currentFields = "&current=temperature_2m,"
                        +"relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,"
                        +"cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,"
                        +"wind_gusts_10m";
        String hourlyFields = "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,"
                        +"precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weather_code,pressure_msl"
                        +",surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility,"
                        +"evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit,wind_speed_10m,wind_speed_80m,"
                        +"wind_speed_120m,wind_direction_10m,wind_direction_80m,wind_direction_120m,temperature_80m,"
                        +"temperature_120m,temperature_180m,soil_temperature_0cm,soil_temperature_6cm,soil_temperature_18cm,"
                        +"uv_index,uv_index_clear_sky,freezing_level_height,sunshine_duration,shortwave_radiation,direct_radiation,"
                        +"diffuse_radiation,direct_normal_irradiance,terrestrial_radiation,shortwave_radiation_instant,"
                        +"direct_radiation_instant,diffuse_radiation_instant,direct_normal_irradiance_instant,"
                        +"terrestrial_radiation_instant";
        String dailyFields = "&daily=weather_code,sunrise,sunset,daylight_duration,sunshine_duration,"
                        +"uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,"
                        +"precipitation_probability_max";
     */

    // Hourly forecast

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public WeatherData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Map<String, String> getCurrentWeatherData() {
        String currentFields = "&current=temperature_2m,"
                        +"relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,"
                        +"cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,"
                        +"wind_gusts_10m";
        String currentWeatherJSON = getWeatherJSON(currentFields);
        Map <String, String> currentWeatherMap = parseCurrentWeatherJSON(currentWeatherJSON, "current");
        return currentWeatherMap;
    }

    public Map<String, String> getHourlyWeatherData() {
        String hourlyFields = "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,"
                        +"precipitation_probability,precipitation,rain,showers,snowfall,snow_depth,weather_code,pressure_msl"
                        +",surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,cloud_cover_high,visibility,"
                        +"evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit,wind_speed_10m,wind_speed_80m,"
                        +"wind_speed_120m,wind_direction_10m,wind_direction_80m,wind_direction_120m,temperature_80m,"
                        +"temperature_120m,temperature_180m,soil_temperature_0cm,soil_temperature_6cm,soil_temperature_18cm,"
                        +"uv_index,uv_index_clear_sky,freezing_level_height,sunshine_duration,shortwave_radiation,direct_radiation,"
                        +"diffuse_radiation,direct_normal_irradiance,terrestrial_radiation,shortwave_radiation_instant,"
                        +"direct_radiation_instant,diffuse_radiation_instant,direct_normal_irradiance_instant,"
                        +"terrestrial_radiation_instant";
        String hourlyWeatherJSON = getWeatherJSON(hourlyFields);
        Map <String, String> hourlyWeatherMap = parseCurrentWeatherJSON(hourlyWeatherJSON, "hourly");
        return hourlyWeatherMap;
    }

    public Map<String, String> getDailyWeatherData() {
        String dailyFields = "&daily=weather_code,sunrise,sunset,daylight_duration,sunshine_duration,"
                        +"uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,precipitation_hours,"
                        +"precipitation_probability_max";
        String dailyWeatherJSON = getWeatherJSON(dailyFields);
        Map <String, String> dailyWeatherMap = parseCurrentWeatherJSON(dailyWeatherJSON, "daily");
        return dailyWeatherMap;
    }

    private Map<String, String> parseCurrentWeatherJSON(String jsonResponse, String responseKey) {
        Map<String, String> values = new HashMap<>();
        try {
            JSONObject json = new JSONObject(jsonResponse);
            // Extract current information
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
        // String baseUrl = "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,wind_speed_10m,relative_humidity_2m";
        String baseUrl = "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f";
        String callUrl = String.format(baseUrl, this.getLatitude(), this.getLongitude()) + requestFields;

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
