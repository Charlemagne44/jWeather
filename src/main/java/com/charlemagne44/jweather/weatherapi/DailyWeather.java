package com.charlemagne44.jweather.weatherapi;

import java.util.List;

import com.google.gson.Gson;

public class DailyWeather {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private Daily_units daily_units;
    private Daily daily;

    public static class Daily_units {
        private String time; // ISO8601 format
        private String weather_code; // WMO code
        private String temperature_2m_max; // Celsius
        private String temperature_2m_min; // Celsius
        private String apparent_temperature_max; // Celsius
        private String apparent_temperature_min; // Celsius
        private String sunrise; // ISO8601 format
        private String sunset; // ISO8601 format
        private String daylight_duration; // Seconds
        private String sunshine_duration; // Seconds
        private String uv_index_max; // String (assuming it can be empty)
        private String uv_index_clear_sky_max; // String (assuming it can be empty)
        private String precipitation_sum; // Millimeters
        private String rain_sum; // Millimeters
        private String showers_sum; // Millimeters
        private String snowfall_sum; // Centimeters
        private String precipitation_hours; // Hours
        private String precipitation_probability_max; // Percentage
        private String wind_speed_10m_max; // Kilometers per hour
        private String wind_gusts_10m_max; // Kilometers per hour
        private String wind_direction_10m_dominant; // Degrees
        private String shortwave_radiation_sum; // MJ/m²
        private String et0_fao_evapotranspiration; // Millimeters
    }

    public static class Daily {
        private List<String> time; // List of ISO8601 format strings
        private List<Integer> weather_code; // List of WMO codes
        private List<Double> temperature_2m_max; // List of Celsius values
        private List<Double> temperature_2m_min; // List of Celsius values
        private List<Double> apparent_temperature_max; // List of Celsius values
        private List<Double> apparent_temperature_min; // List of Celsius values
        private List<String> sunrise; // List of ISO8601 format strings
        private List<String> sunset; // List of ISO8601 format strings
        private List<Double> daylight_duration; // List of seconds
        private List<Double> sunshine_duration; // List of seconds
        private List<String> uv_index_max; // List of strings (assuming it can be empty)
        private List<String> uv_index_clear_sky_max; // List of strings (assuming it can be empty)
        private List<Double> precipitation_sum; // List of millimeters
        private List<Double> rain_sum; // List of millimeters
        private List<Double> showers_sum; // List of millimeters
        private List<Double> snowfall_sum; // List of centimeters
        private List<Double> precipitation_hours; // List of hours
        private List<Integer> precipitation_probability_max; // List of percentages
        private List<Double> wind_speed_10m_max; // List of kilometers per hour
        private List<Double> wind_gusts_10m_max; // List of kilometers per hour
        private List<Integer> wind_direction_10m_dominant; // List of degrees
        private List<Double> shortwave_radiation_sum; // List of MJ/m²
        private List<Double> et0_fao_evapotranspiration; // List of millimeters

        public List<String> getTime() {
            return time;
        }

        public List<Integer> getWeather_code() {
            return weather_code;
        }

        public List<Double> getTemperature_2m_max() {
            return temperature_2m_max;
        }

        public List<Double> getTemperature_2m_min() {
            return temperature_2m_min;
        }

        public List<Double> getApparent_temperature_max() {
            return apparent_temperature_max;
        }

        public List<Double> getApparent_temperature_min() {
            return apparent_temperature_min;
        }

        public List<String> getSunrise() {
            return sunrise;
        }

        public List<String> getSunset() {
            return sunset;
        }

        public List<Double> getDaylight_duration() {
            return daylight_duration;
        }

        public List<Double> getSunshine_duration() {
            return sunshine_duration;
        }

        public List<String> getUv_index_max() {
            return uv_index_max;
        }

        public List<String> getUv_index_clear_sky_max() {
            return uv_index_clear_sky_max;
        }

        public List<Double> getPrecipitation_sum() {
            return precipitation_sum;
        }

        public List<Double> getRain_sum() {
            return rain_sum;
        }

        public List<Double> getShowers_sum() {
            return showers_sum;
        }

        public List<Double> getSnowfall_sum() {
            return snowfall_sum;
        }

        public List<Double> getPrecipitation_hours() {
            return precipitation_hours;
        }

        public List<Integer> getPrecipitation_probability_max() {
            return precipitation_probability_max;
        }

        public List<Double> getWind_speed_10m_max() {
            return wind_speed_10m_max;
        }

        public List<Double> getWind_gusts_10m_max() {
            return wind_gusts_10m_max;
        }

        public List<Integer> getWind_direction_10m_dominant() {
            return wind_direction_10m_dominant;
        }

        public List<Double> getShortwave_radiation_sum() {
            return shortwave_radiation_sum;
        }

        public List<Double> getEt0_fao_evapotranspiration() {
            return et0_fao_evapotranspiration;
        }

    }

    public static DailyWeather fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, DailyWeather.class);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getGenerationtime_ms() {
        return generationtime_ms;
    }

    public int getUtc_offset_seconds() {
        return utc_offset_seconds;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getTimezone_abbreviation() {
        return timezone_abbreviation;
    }

    public double getElevation() {
        return elevation;
    }

    public Daily_units getDaily_units() {
        return daily_units;
    }

    public Daily getDaily() {
        return daily;
    }

}
