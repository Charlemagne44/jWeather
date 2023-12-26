package com.charlemagne44.jweather;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;
import java.util.List;
import java.util.Map;

public class App 
{
    public static void main( String[] args )
    {
        // Use Geocoding to get location lat and long from more common types
        String loc = "Nigeria";
        List<Double> latlong = Geocode.getLatLong(loc);
        double latitude = latlong.get(0);
        double longitude = latlong.get(1);
        System.out.printf("Lat long extracted: %f %f from location: %s\n", latitude, longitude, loc);

        // Get weather data
        WeatherData data = new WeatherData(latitude, longitude);
        Map<String,String> currentWeather = data.getCurrentWeatherData();
        System.out.println(currentWeather);

        Map<String,String> hourlyWeather = data.getHourlyWeatherData();
        System.out.println(hourlyWeather);

        Map<String,String> dailyWeather = data.getDailyWeatherData();
        System.out.println(dailyWeather);
    }
}
