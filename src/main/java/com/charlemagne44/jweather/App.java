package com.charlemagne44.jweather;

import com.charlemagne44.jweather.weatherapi.WeatherData;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WeatherData data = new WeatherData((long)50.0, (long)21.0);
        String weather_data = data.getWeatherData();
        System.out.println(weather_data);
    }
}
