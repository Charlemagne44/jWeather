package com.charlemagne44.jweather.weatherapi;

import com.google.gson.Gson;

public class CurrentWeather {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private Current_units current_units;
    private Current current;

    public static class Current_units {
        private String time; // ISO8601 format
        private String interval; // Seconds
        private String temperature_2m; // Celsius
        private String relative_humidity_2m; // Percentage
        private String apparent_temperature; // Celsius
        private String is_day; // Assuming it's a boolean or a string representing day/night
        private String precipitation; // Millimeters
        private String rain; // Millimeters
        private String showers; // Millimeters
        private String snowfall; // Centimeters
        private String weather_code; // WMO code
        private String cloud_cover; // Percentage
        private String pressure_msl; // Hectopascals
        private String surface_pressure; // Hectopascals
        private String wind_speed_10m; // Kilometers per hour
        private String wind_direction_10m; // Degrees
        private String wind_gusts_10m; // Kilometers per hour

        public String getTime() {
            return time;
        }

        public String getInterval() {
            return interval;
        }

        public String getTemperature_2m() {
            return temperature_2m;
        }

        public String getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public String getApparent_temperature() {
            return apparent_temperature;
        }

        public String getIs_day() {
            return is_day;
        }

        public String getPrecipitation() {
            return precipitation;
        }

        public String getRain() {
            return rain;
        }

        public String getShowers() {
            return showers;
        }

        public String getSnowfall() {
            return snowfall;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public String getCloud_cover() {
            return cloud_cover;
        }

        public String getPressure_msl() {
            return pressure_msl;
        }

        public String getSurface_pressure() {
            return surface_pressure;
        }

        public String getWind_speed_10m() {
            return wind_speed_10m;
        }

        public String getWind_direction_10m() {
            return wind_direction_10m;
        }

        public String getWind_gusts_10m() {
            return wind_gusts_10m;
        }

    }

    public static class Current {
        private String time; // ISO8601 format
        private int interval; // Seconds
        private double temperature_2m; // Celsius
        private int relative_humidity_2m; // Percentage
        private double apparent_temperature; // Celsius
        private int is_day; // Assuming it's a boolean or a flag
        private double precipitation; // Millimeters
        private double rain; // Millimeters
        private double showers; // Millimeters
        private double snowfall; // Centimeters
        private int weather_code; // WMO code
        private int cloud_cover; // Percentage
        private double pressure_msl; // Hectopascals
        private double surface_pressure; // Hectopascals
        private double wind_speed_10m; // Kilometers per hour
        private int wind_direction_10m; // Degrees
        private double wind_gusts_10m; // Kilometers per hour

        public String getTime() {
            return time;
        }

        public int getInterval() {
            return interval;
        }

        public double getTemperature_2m() {
            return temperature_2m;
        }

        public int getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public double getApparent_temperature() {
            return apparent_temperature;
        }

        public int getIs_day() {
            return is_day;
        }

        public double getPrecipitation() {
            return precipitation;
        }

        public double getRain() {
            return rain;
        }

        public double getShowers() {
            return showers;
        }

        public double getSnowfall() {
            return snowfall;
        }

        public int getWeather_code() {
            return weather_code;
        }

        public int getCloud_cover() {
            return cloud_cover;
        }

        public double getPressure_msl() {
            return pressure_msl;
        }

        public double getSurface_pressure() {
            return surface_pressure;
        }

        public double getWind_speed_10m() {
            return wind_speed_10m;
        }

        public int getWind_direction_10m() {
            return wind_direction_10m;
        }

        public double getWind_gusts_10m() {
            return wind_gusts_10m;
        }
    }

    public static CurrentWeather fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, CurrentWeather.class);
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

    public Current_units getCurrent_units() {
        return current_units;
    }

    public Current getCurrent() {
        return current;
    }

}
