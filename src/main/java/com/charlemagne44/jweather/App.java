package com.charlemagne44.jweather;

import com.charlemagne44.jweather.weatherapi.WeatherData;

public class App 
{
    public static void main( String[] args )
    {
        WeatherData data = new WeatherData(50.0, 21.0);
        System.out.printf("Current time: %s, Current Temp %s, Current Humidity: %s\n", data.getCurrentTime(), data.getCurrentTemp(), data.getCurrentHumidty());
    }
}
