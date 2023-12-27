package com.charlemagne44.jweather.frontend;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;

import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TLabel;
import jexer.TProgressBar;
import jexer.TWindow;

public class HomePage {
    // UI Elements
    private TApplication app;
    private TWindow window;
    private TField cityInputField;
    private TLabel temperatureLabel;
    private TLabel pressureLabel;
    private TLabel humidityLabel;
    private TLabel windLabel;
    private TProgressBar progressBar;

    // Futures and Executors for async
    private CompletableFuture<Integer> weatherUpdateFuture;
    private ScheduledExecutorService progressBarExecutor;

    // Offsets for labels
    private int label_x_offset;
    private int label_y_offset = 1;

    TAction inputAction = new TAction() {
        @Override
        public void DO() {
            if (source instanceof jexer.TField) {
                // Set progress bar to 0 and make lables loading
                progressBar.setValue(0);
                temperatureLabel.setLabel("Loading...");
                pressureLabel.setLabel("Loading...");
                humidityLabel.setLabel("Loading...");
                windLabel.setLabel("Loading...");

                // Fetch the input city
                String inputCity = ((jexer.TField) source).getText();

                // Async update city labels
                weatherUpdateFuture = CompletableFuture.supplyAsync(() -> updateWeatherLabels(inputCity));

                // Begin updating the progress bar ever 1/4 second
                progressBarExecutor = Executors.newSingleThreadScheduledExecutor();
                progressBarExecutor.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        updateProgressBar();
                    }
                }, 0, 250, TimeUnit.MILLISECONDS);
            }
        }
    };

    public HomePage(TApplication application) {
        // The app that hosts other windows
        this.app = application;

        // Window that our app exists in
        this.window = new TWindow(this.app, "Weather Homepage", 50, 30);

        // City Input
        this.cityInputField = new TField(this.window, 0, 0, 30, false, "Enter a City", inputAction);

        // Labels
        this.temperatureLabel = new TLabel(this.window, "Current Temp: ", label_x_offset, label_y_offset + 0);
        this.pressureLabel = new TLabel(this.window, "Current Pressure: ", label_x_offset, label_y_offset + 1);
        this.humidityLabel = new TLabel(this.window, "Current Humidity: ", label_x_offset, label_y_offset + 2);
        this.windLabel = new TLabel(this.window, "Current Wind: ", label_x_offset, label_y_offset + 3);

        // Progress bar
        this.progressBar = new TProgressBar(this.window, label_x_offset, label_y_offset + 4, 30, 50);
    }

    private Integer updateWeatherLabels(String location) {
        // Fetch city location
        List<Double> cords = Geocode.getLatLong(location);
        Double lat = cords.get(0);
        Double lon = cords.get(1);

        // Fetch weather for location
        WeatherData myD = new WeatherData(lat, lon);
        Map<String, String> weatherdata = myD.getCurrentWeatherData();

        // Update labels with data
        temperatureLabel.setLabel("Current Temp: " + weatherdata.get("temperature_2m") + "°C");
        pressureLabel.setLabel("Current Pressure: " + weatherdata.get("surface_pressure") + " mmHg");
        humidityLabel.setLabel("Current Humidity: " + weatherdata.get("relative_humidity_2m") + "%");
        windLabel.setLabel("Current Wind: " + weatherdata.get("wind_speed_10m") + "km/h");

        // Stop the progress bar from making updates
        this.progressBarExecutor.shutdown();
        // Jump progress bar to 100%
        this.progressBar.setValue(100);

        return 0;
    }

    private void updateProgressBar() {
        // Increment the progress bar by 5, if value > 100 start over
        int currentValue = this.progressBar.getValue();
        currentValue = currentValue + 5;
        if (currentValue > 100) {
            currentValue = 5;
        } else {
            this.progressBar.setValue(currentValue);
        }
    }

}