package com.charlemagne44.jweather.geocodingapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.charlemagne44.jweather.geocodingapi.Geocode.LatLong;

public class GeocodeTest {
    @Test
    public void TestGetLatLong() {
        String cityName = "New York City";
        Geocode geocoder = new Geocode();
        LatLong latlong = geocoder.getLatLong(cityName);

        System.out.println(latlong);
        assertEquals(40.7128, latlong.latitude, 1.0);
        assertEquals(-74.0060, latlong.longitude, 1.0);
    }
}
