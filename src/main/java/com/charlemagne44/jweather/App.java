package com.charlemagne44.jweather;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        // myWindow.addProgressBar(1, 6, 20, 50);
        // Implement this in the future, but figure out why it disappears on updating
        // the other fields
        // TProgressBar myBar = new TProgressBar(currentWind, 1, 6, 20, 50);

        // I made updating the labels way more complicated than it needs to be lol
        TAction myAction = new TAction() {
            @Override
            public void DO() {
                if (source instanceof jexer.TField) {
                    String customT = ((jexer.TField) source).getText();

                    List<Double> cords = Geocode.getLatLong(customT);
                    Double lat = cords.get(0);
                    Double lon = cords.get(1);

                    WeatherData myD = new WeatherData(lat, lon);
                    Map<String, String> currentW = myD.getCurrentWeatherData();

                    // Remove labels from the display
                    for (int index = 0; index < labelList.size(); index++) {
                        myWindow.remove(labelList.get(index));
                    }
                    // Remove the old labels from the list here
                    labelList.clear();

                    // There's def a cuter way to do this, updating the label information
                    labelList.add(myWindow.addLabel("Current Temp: " + currentW.get("temperature_2m") + "°C",
                            label_offset, label_y_offset + 0));
                    labelList.add(myWindow.addLabel(
                            "Current Pressure: " + currentW.get("surface_pressure") + " mmHg", label_offset,
                            label_y_offset + 1));
                    labelList.add(myWindow.addLabel("Current Humidity: " + currentW.get("relative_humidity_2m") + "%",
                            label_offset, label_y_offset + 2));
                    labelList.add(myWindow.addLabel("Current Wind: " + currentW.get("wind_speed_10m") + "km/h",
                            label_offset, label_y_offset + 3));
                }
            }
        };
        myWindow.addField(0, 0, 20, false, "Enter a City", myAction);
        // myWindow.addText("The Current Temp Is", 3, 3, 20, 10);

        app.run();
    }
}
