package com.charlemagne44.jweather;

import com.charlemagne44.jweather.frontend.HomePage;
import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

import jexer.TAction;
import jexer.TApplication;
import jexer.TButton;
import jexer.TEditColorThemeWindow;
import jexer.TField;
import jexer.TLabel;
import jexer.TProgressBar;
import jexer.TText;
import jexer.TWindow;

public class App {
    public static void main(String[] args) throws Exception {

        TApplication app = new TApplication(TApplication.BackendType.XTERM);
        app.addToolMenu();
        app.addFileMenu();
        app.addWindowMenu();

        int label_offset = 1;
        int label_y_offset = 1;
        ArrayList<TLabel> labelList = new ArrayList<TLabel>();

        TWindow myWindow = new TWindow(app, "MyWindow 2", 80, 25);

        TLabel currentTemp = myWindow.addLabel("Current Temp: ", label_offset, label_y_offset + 0); // "temperature_2m"
        labelList.add(currentTemp);
        TLabel currentPressure = myWindow.addLabel("Current Pressure: ", label_offset, label_y_offset + 1); // "surface_pressure"
        labelList.add(currentPressure);
        TLabel currentHumidity = myWindow.addLabel("Current Humidity: ", label_offset, label_y_offset + 2); // "relative_humidity_2m"
        labelList.add(currentHumidity);
        TLabel currentWind = myWindow.addLabel("Current Wind: ", label_offset, label_y_offset + 3); // "wind_speed_10m"
        labelList.add(currentWind);

        TProgressBar progressBar = new TProgressBar(currentWind, 0, 1, 30, 1);

        // This is ridiculous and needs to be refactored, the entire UI shold probably
        // be its own class
        TAction myAction = new TAction() {
            @Override
            public void DO() {
                if (source instanceof jexer.TField) {
                    String customT = ((jexer.TField) source).getText();
                    ScheduledExecutorService myExecutorService = Executors.newSingleThreadScheduledExecutor();
                    myExecutorService.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            int currentValue = progressBar.getValue();
                            currentValue = currentValue + 5;
                            if (currentValue > 100) {
                                currentValue = 5;
                            } else {
                                progressBar.setValue(currentValue);
                            }
                        }
                    }, 0, 250, TimeUnit.MILLISECONDS);

                    CompletableFuture<Map<String, String>> completableFuture = CompletableFuture
                            .supplyAsync(() -> fetchWeatherData(customT, currentTemp, currentPressure, currentHumidity,
                                    currentWind, myExecutorService, progressBar));

                    progressBar.setValue(0);
                    currentTemp.setLabel("Loading...");
                    currentPressure.setLabel("Loading...");
                    currentHumidity.setLabel("Loading...");
                    currentWind.setLabel("Loading...");
                }
            }
        };

        myWindow.addField(0, 0, 20, false, "Enter a City", myAction);
        HomePage myHomePage = new HomePage(app);
        app.run();
    }

    public static Map<String, String> fetchWeatherData(String location, TLabel currentTemp, TLabel currentPressure,
            TLabel currentHumidity, TLabel currentWind, ScheduledExecutorService ses, TProgressBar bar) {

        List<Double> cords = Geocode.getLatLong(location);

        Double lat = cords.get(0);
        Double lon = cords.get(1);
        WeatherData myD = new WeatherData(lat, lon);
        Map<String, String> weatherdata = myD.getCurrentWeatherData();
        currentTemp.setLabel("Current Temp: " + weatherdata.get("temperature_2m") + "°C");
        currentPressure.setLabel("Current Pressure: " + weatherdata.get("surface_pressure") + " mmHg");
        currentHumidity.setLabel("Current Humidity: " + weatherdata.get("relative_humidity_2m") + "%");
        currentWind.setLabel("Current Wind: " + weatherdata.get("wind_speed_10m") + "km/h");

        ses.shutdown();
        bar.setValue(100);

        return weatherdata;
    }
}
