package com.charlemagne44.jweather.geocodingapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.charlemagne44.jweather.geocodingapi.Geocode.LatLong;

public class GeocodeTest {
    @Test
    public void TestGetLatLong() {
        String cityName = "New York";
        Geocode geocoder = new Geocode();
        LatLong latlong = geocoder.getLatLong(cityName);

        assertEquals(latlong.latitude, 35.7251496, 1.0);
        assertEquals(latlong.longitude, 139.7630049, 1.0);
    }
}
