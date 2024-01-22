package com.charlemagne44.weatherapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import com.charlemagne44.jweather.weatherapi.WeatherData;

public class WeatherDataTest {
    @Test
    // @Ignore("Ignore any tests with real API calls")
    public void TestGetCurrentWeather() {
        WeatherData data = new WeatherData();
        data.setCurrentWeather(30.0, 30.0);
        JSONObject response = data.getCurrentWeather();
        try {
            System.out.println(response);
            JSONObject current = response.getJSONObject("current");
            assertNotEquals(current.getString("temperature_2m"), null);
            assertNotEquals(current.getString("relative_humidity"), null);
            assertEquals(current.getString("fake_key"), null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
