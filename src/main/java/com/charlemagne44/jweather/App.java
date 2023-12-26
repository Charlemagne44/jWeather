package com.charlemagne44.jweather;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;

import javafx.scene.control.Button;

import java.util.List;
import java.util.Map;

import jexer.TAction;
import jexer.TApplication;
import jexer.TButton;
import jexer.TField;
import jexer.TWindow;

public class App {
    public static void main(String[] args) throws Exception {
        WeatherData myWeather = new WeatherData(42.361145, -71.057083);

        double mydata = myWeather.getLatitude();

        TApplication app = new TApplication(TApplication.BackendType.XTERM);
        app.addToolMenu();
        app.addFileMenu();
        app.addWindowMenu();

        TWindow myWindow = new TWindow(app, "MyWindow 2", 50, 25);

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

                    myWindow.addText(currentW.toString(), 1, 1, 40, 20);
                }
            }
        };
        // TField myField = new TField(myWindow, 0, 0, 30, false, "Press This",
        // myAction);
        myWindow.addField(0, 0, 30, false, "Enter a City", myAction);

        // app.addWindow("Get Weather", 50, 50).addButton("Press Me", 10, 10,
        // myAction).activate();

        app.run();

    }
}
