package com.charlemagne44.jweather.weatherapi;

import java.util.List;

import com.google.gson.Gson;

public class HourlyWeather {
    private double latitude;
    private double longitude;
    private double generationtime_ms;
    private int utc_offset_seconds;
    private String timezone;
    private String timezone_abbreviation;
    private double elevation;
    private Hourly_units hourly_units;
    private Hourly hourly;

    public static class Hourly_units {
        private String time; // ISO8601 format
        private String temperature_2m; // Celsius
        private String relative_humidity_2m; // Percentage
        private String dew_point_2m; // Celsius
        private String apparent_temperature; // Celsius
        private String precipitation_probability; // Percentage
        private String precipitation; // Millimeters
        private String rain; // Millimeters
        private String showers; // Millimeters
        private String snowfall; // Centimeters
        private String snow_depth; // Meters
        private String weather_code; // WMO code
        private String pressure_msl; // Hectopascals
        private String surface_pressure; // Hectopascals
        private String cloud_cover; // Percentage
        private String cloud_cover_low; // Percentage
        private String cloud_cover_mid; // Percentage
        private String cloud_cover_high; // Percentage
        private String visibility; // Meters
        private String evapotranspiration; // Millimeters
        private String et0_fao_evapotranspiration; // Millimeters
        private String vapour_pressure_deficit; // Kilopascals
        private String wind_speed_10m; // Kilometers per hour
        private String wind_direction_10m; // Degrees
        private String wind_gusts_10m; // Kilometers per hour
        private String soil_temperature_6cm; // Celsius
        private String soil_moisture_3_to_9cm; // Cubic meters per cubic meter

        public String getTime() {
            return time;
        }

        public String getTemperature_2m() {
            return temperature_2m;
        }

        public String getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public String getDew_point_2m() {
            return dew_point_2m;
        }

        public String getApparent_temperature() {
            return apparent_temperature;
        }

        public String getPrecipitation_probability() {
            return precipitation_probability;
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

        public String getSnow_depth() {
            return snow_depth;
        }

        public String getWeather_code() {
            return weather_code;
        }

        public String getPressure_msl() {
            return pressure_msl;
        }

        public String getSurface_pressure() {
            return surface_pressure;
        }

        public String getCloud_cover() {
            return cloud_cover;
        }

        public String getCloud_cover_low() {
            return cloud_cover_low;
        }

        public String getCloud_cover_mid() {
            return cloud_cover_mid;
        }

        public String getCloud_cover_high() {
            return cloud_cover_high;
        }

        public String getVisibility() {
            return visibility;
        }

        public String getEvapotranspiration() {
            return evapotranspiration;
        }

        public String getEt0_fao_evapotranspiration() {
            return et0_fao_evapotranspiration;
        }

        public String getVapour_pressure_deficit() {
            return vapour_pressure_deficit;
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

        public String getSoil_temperature_6cm() {
            return soil_temperature_6cm;
        }

        public String getSoil_moisture_3_to_9cm() {
            return soil_moisture_3_to_9cm;
        }

    }

    public static class Hourly {
        private List<String> time; // ISO8601 format
        private List<Double> temperature_2m; // Celsius
        private List<Integer> relative_humidity_2m; // Percentage
        private List<Double> dew_point_2m; // Celsius
        private List<Double> apparent_temperature; // Celsius
        private List<Integer> precipitation_probability; // Percentage
        private List<Double> precipitation; // Millimeters
        private List<Double> rain; // Millimeters
        private List<Double> showers; // Millimeters
        private List<Double> snowfall; // Centimeters
        private List<Double> snow_depth; // Meters
        private List<Integer> weather_code; // WMO code
        private List<Double> pressure_msl; // Hectopascals
        private List<Double> surface_pressure; // Hectopascals
        private List<Integer> cloud_cover; // Percentage
        private List<Integer> cloud_cover_low; // Percentage
        private List<Integer> cloud_cover_mid; // Percentage
        private List<Integer> cloud_cover_high; // Percentage
        private List<Double> visibility; // Meters
        private List<Double> evapotranspiration; // Millimeters
        private List<Double> et0_fao_evapotranspiration; // Millimeters
        private List<Double> vapour_pressure_deficit; // Kilopascals
        private List<Double> wind_speed_10m; // Kilometers per hour
        private List<Double> wind_direction_10m; // Degrees
        private List<Double> wind_gusts_10m; // Kilometers per hour
        private List<Double> soil_temperature_6cm; // Celsius
        private List<Double> soil_moisture_3_to_9cm; // Cubic meter

        public List<String> getTime() {
            return time;
        }

        public List<Double> getTemperature_2m() {
            return temperature_2m;
        }

        public List<Integer> getRelative_humidity_2m() {
            return relative_humidity_2m;
        }

        public List<Double> getDew_point_2m() {
            return dew_point_2m;
        }

        public List<Double> getApparent_temperature() {
            return apparent_temperature;
        }

        public List<Integer> getPrecipitation_probability() {
            return precipitation_probability;
        }

        public List<Double> getPrecipitation() {
            return precipitation;
        }

        public List<Double> getRain() {
            return rain;
        }

        public List<Double> getShowers() {
            return showers;
        }

        public List<Double> getSnowfall() {
            return snowfall;
        }

        public List<Double> getSnow_depth() {
            return snow_depth;
        }

        public List<Integer> getWeather_code() {
            return weather_code;
        }

        public List<Double> getPressure_msl() {
            return pressure_msl;
        }

        public List<Double> getSurface_pressure() {
            return surface_pressure;
        }

        public List<Integer> getCloud_cover() {
            return cloud_cover;
        }

        public List<Integer> getCloud_cover_low() {
            return cloud_cover_low;
        }

        public List<Integer> getCloud_cover_mid() {
            return cloud_cover_mid;
        }

        public List<Integer> getCloud_cover_high() {
            return cloud_cover_high;
        }

        public List<Double> getVisibility() {
            return visibility;
        }

        public List<Double> getEvapotranspiration() {
            return evapotranspiration;
        }

        public List<Double> getEt0_fao_evapotranspiration() {
            return et0_fao_evapotranspiration;
        }

        public List<Double> getVapour_pressure_deficit() {
            return vapour_pressure_deficit;
        }

        public List<Double> getWind_speed_10m() {
            return wind_speed_10m;
        }

        public List<Double> getWind_direction_10m() {
            return wind_direction_10m;
        }

        public List<Double> getWind_gusts_10m() {
            return wind_gusts_10m;
        }

        public List<Double> getSoil_temperature_6cm() {
            return soil_temperature_6cm;
        }

        public List<Double> getSoil_moisture_3_to_9cm() {
            return soil_moisture_3_to_9cm;
        }

    }

    public static HourlyWeather fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, HourlyWeather.class);
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

    public Hourly_units getHourly_units() {
        return hourly_units;
    }

    public Hourly getHourly() {
        return hourly;
    }

}
