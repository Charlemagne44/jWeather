package com.charlemagne44.jweather.apirequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

public class ApirequestTest {
    @Test
    @Ignore("Ignore automation for test with Real API calls")
    public void TestRequest() {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&current=temperature_2m&timezone=auto";
        String string_response = Apirequest.request(url);
        try {
            JSONObject response  = new JSONObject(string_response);
            JSONObject current = response.getJSONObject("current");
            assertNotEquals(current.getString("temperature_2m"), null); // temperature 2m should be present
            assertEquals(current.getString("relative_humidity"), null); // relative humidity should be absent
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
