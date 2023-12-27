package com.charlemagne44.jweather;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
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

        HashSet<ScheduledExecutorService> subscriptions = new HashSet<ScheduledExecutorService>();

        int label_offset = 1;
        int label_y_offset = 1;
        ArrayList<TLabel> labelList = new ArrayList<TLabel>();

        TWindow myWindow = new TWindow(app, "MyWindow 2", 80, 25);
        // TText currentTemp = new TText(myWindow, "Current Temp: ", 0, 3, 0, 0,
        // "blue");
        // TText currentTemp = new TText(myWindow, "Current Temp: ", 0, 3, 0, 0,
        // "blue");

        // This is longer than it needs to be imo
        TLabel currentTemp = myWindow.addLabel("Current Temp: ", label_offset, label_y_offset + 0); // "temperature_2m"
        labelList.add(currentTemp);
        TLabel currentPressure = myWindow.addLabel("Current Pressure: ", label_offset, label_y_offset + 1); // "surface_pressure"
        labelList.add(currentPressure);
        TLabel currentHumidity = myWindow.addLabel("Current Humidity: ", label_offset, label_y_offset + 2); // "relative_humidity_2m"
        labelList.add(currentHumidity);
        TLabel currentWind = myWindow.addLabel("Current Wind: ", label_offset, label_y_offset + 3); // "wind_speed_10m"
        labelList.add(currentWind);

        TProgressBar progressBar = new TProgressBar(currentWind, 0, 1, 30, 1);

        TAction myAction = new TAction() {
            @Override
            public void DO() {
                // testAction.DO();
                if (source instanceof jexer.TField) {
                    String customT = ((jexer.TField) source).getText();
                    CompletableFuture<Map<String, String>> completableFuture = CompletableFuture
                            .supplyAsync(() -> fetchWeatherData(customT, currentTemp, currentPressure, currentHumidity,
                                    currentWind));

                    currentTemp.setLabel("Loading...");
                    currentPressure.setLabel("Loading...");
                    currentHumidity.setLabel("Loading...");
                    currentWind.setLabel("Loading...");
                    // while (!completableFuture.isDone()) {
                    // Just blocking here?
                    // }
                    // List<Double> cords = Geocode.getLatLong(customT);

                    // Double lat = cords.get(0);
                    // Double lon = cords.get(1);

                    // WeatherData myD = new WeatherData(lat, lon);

                    // Map<String, String> currentW;
                    // try {
                    // currentW = completableFuture.get();

                    // currentTemp.setLabel("Current Temp: " + currentW.get("temperature_2m") +
                    // "°C");
                    // currentPressure.setLabel("Current Pressure: " +
                    // currentW.get("surface_pressure") + " mmHg");
                    // currentHumidity.setLabel("Current Humidity: " +
                    // currentW.get("relative_humidity_2m") + "%");
                    // currentWind.setLabel("Current Wind: " + currentW.get("wind_speed_10m") +
                    // "km/h");
                    // } catch (InterruptedException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // } catch (ExecutionException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // } // myD.getCurrentWeatherData();
                }
            }
        };

        myWindow.addField(0, 0, 20, false, "Enter a City", myAction);

        app.run();
    }

    public static Map<String, String> fetchWeatherData(String location, TLabel currentTemp, TLabel currentPressure,
            TLabel currentHumidity, TLabel currentWind) {

        List<Double> cords = Geocode.getLatLong(location);

        Double lat = cords.get(0);
        Double lon = cords.get(1);
        WeatherData myD = new WeatherData(lat, lon);
        Map<String, String> weatherdata = myD.getCurrentWeatherData();
        currentTemp.setLabel("Current Temp: " + weatherdata.get("temperature_2m") + "°C");
        currentPressure.setLabel("Current Pressure: " + weatherdata.get("surface_pressure") + " mmHg");
        currentHumidity.setLabel("Current Humidity: " + weatherdata.get("relative_humidity_2m") + "%");
        currentWind.setLabel("Current Wind: " + weatherdata.get("wind_speed_10m") + "km/h");

        return weatherdata;
    }

    public static void test(TLabel label) {
        label.setLabel("Loading");
    }
}
