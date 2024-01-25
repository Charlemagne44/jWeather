package com.charlemagne44.jweather.weatherapi;

import static org.junit.Assert.assertNotEquals;

import org.junit.Ignore;
import org.junit.Test;

public class WeatherDataTest {
    @Test
    @Ignore("Ignore automation for test with Real API calls")
    public void TestGetCurrentWeather() {
        WeatherData data = new WeatherData();
        data.setCurrentWeather(30.0, 30.0);
        assertNotEquals(null, data.getCurrentWeather().getCurrent().getTemperature_2m());
        assertNotEquals(null, data.getCurrentWeather().getCurrent().getRelative_humidity_2m());

        data.setHourlyWeather(30.0, 30.0);
        assertNotEquals(null, data.getHourlyWeather().getHourly().getTemperature_2m().get(10));
        assertNotEquals(null, data.getHourlyWeather().getHourly().getPrecipitation().get(10));

        data.setDailyWeather(30.0, 30.0);
        assertNotEquals(null, data.getDailyWeather().getDaily().getApparent_temperature_max());
        assertNotEquals(null, data.getDailyWeather().getDaily().getApparent_temperature_min());
    }
}
