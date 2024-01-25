package com.charlemagne44.jweather;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.geocodingapi.Geocode.LatLong;
import com.charlemagne44.jweather.weatherapi.WeatherData;

public class App {
    public static void main(String[] args) {
        LatLong latlong = Geocode.getLatLong("New York City");
        WeatherData data = new WeatherData();
        data.setCurrentWeather(latlong.latitude, latlong.longitude);
        System.out.println(data.getCurrentWeather());

        data.setHourlyWeather(latlong.latitude, latlong.longitude);
        System.out.println(data.getHourlyWeather());

        data.setDailyWeather(latlong.latitude, latlong.longitude);
        System.out.println(data.getDailyWeather());

    }
}
