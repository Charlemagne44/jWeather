package com.charlemagne44.jweather.geocodingapi;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

import com.charlemagne44.jweather.geocodingapi.Geocode.LatLong;

public class GeocodeTest {
    @Test
    @Ignore("Ignore automation for test with Real API calls")
    public void TestGetLatLong() {
        String cityName = "New York City";
        LatLong latlong = Geocode.getLatLong(cityName);

        System.out.println(latlong);
        assertEquals(40.7128, latlong.latitude, 1.0);
        assertEquals(-74.0060, latlong.longitude, 1.0);
    }
}
