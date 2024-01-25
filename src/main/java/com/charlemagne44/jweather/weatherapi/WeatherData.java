package com.charlemagne44.jweather.weatherapi;

import org.json.JSONException;
import org.json.JSONObject;

import com.charlemagne44.jweather.apirequest.Apirequest;

public class WeatherData {
    JSONObject currentWeather;
    JSONObject hourlyWeather;
    JSONObject dailyWeather;

    static String currentRequest = String.join("",
            "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=temperature_2m,",
            "relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,",
            "cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m&timezone=auto");

    static String hourlyRequest = String.join("",
            "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=wind_speed_10m,",
            "wind_direction_10m,wind_gusts_10m&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,",
            "apparent_temperature,precipitation_probability,precipitation,rain,showers,snowfall,",
            "snow_depth,weather_code,pressure_msl,surface_pressure,cloud_cover,cloud_cover_low,cloud_cover_mid,",
            "cloud_cover_high,visibility,evapotranspiration,et0_fao_evapotranspiration,vapour_pressure_deficit,",
            "wind_speed_10m,wind_direction_10m,wind_gusts_10m,soil_temperature_6cm,soil_moisture_3_to_9cm",
            "&timezone=auto&forecast_days=2");

    static String dailyRequest = String.join("",
            "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current=wind_speed_10m,",
            "wind_direction_10m,wind_gusts_10m&daily=weather_code,temperature_2m_max,temperature_2m_min,",
            "apparent_temperature_max,apparent_temperature_min,sunrise,sunset,daylight_duration,",
            "sunshine_duration,uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,",
            "snowfall_sum,precipitation_hours,precipitation_probability_max,wind_speed_10m_max,wind_gusts_10m_max,",
            "wind_direction_10m_dominant,shortwave_radiation_sum,et0_fao_evapotranspiration&timezone=auto");

    public void setCurrentWeather(double latitude, double longitude) {
        String constructedURL = String.format(currentRequest, latitude, longitude);
        try {
            JSONObject jsonResponse = new JSONObject(Apirequest.request(constructedURL));
            this.currentWeather = jsonResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setHourlyWeather(double latitude, double longitude) {
        String constructedURL = String.format(hourlyRequest, latitude, longitude);
        try {
            JSONObject jsonResponse = new JSONObject(Apirequest.request(constructedURL));
            this.hourlyWeather = jsonResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setDailyWeather(double latitude, double longitude) {
        String constructedURL = String.format(dailyRequest, latitude, longitude);
        try {
            JSONObject jsonResponse = new JSONObject(Apirequest.request(constructedURL));
            this.dailyWeather = jsonResponse;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getCurrentWeather() {
        return currentWeather;
    }

    public JSONObject getHourlyWeather() {
        return hourlyWeather;
    }

    public JSONObject getDailyWeather() {
        return dailyWeather;
    }

};
