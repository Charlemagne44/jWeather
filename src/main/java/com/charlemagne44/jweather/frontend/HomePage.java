package com.charlemagne44.jweather.frontend;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.charlemagne44.jweather.geocodingapi.Geocode;
import com.charlemagne44.jweather.weatherapi.WeatherData;

import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TImage;
import jexer.TLabel;
import jexer.TProgressBar;
import jexer.TText;
import jexer.TWindow;

public class HomePage {

    // UI Elements
    private TApplication app;
    private TWindow window;
    private TField cityInputField;
    private TLabel temperatureLabel;
    private TLabel precipitationLabel;
    private TLabel pressureLabel;
    private TLabel humidityLabel;
    private TLabel windLabel;
    private TProgressBar progressBar;
    private TText debugText;
    private TImage testImage;

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
                precipitationLabel.setLabel("Loading...");
                pressureLabel.setLabel("Loading...");
                humidityLabel.setLabel("Loading...");
                windLabel.setLabel("Loading...");

                // Fetch the input city
                String inputCity = ((jexer.TField) source).getText();

                // Async update city labels
                weatherUpdateFuture = CompletableFuture.supplyAsync(() -> updateWeatherLabels(inputCity));

                // updateWeatherLabels(inputCity);

                // Begin updating the progress bar ever 1/4 second
                progressBarExecutor = Executors.newSingleThreadScheduledExecutor();
                progressBarExecutor.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        updateProgressBar();
                    }
                }, 0, 250, TimeUnit.MILLISECONDS);

                // getWeatherImage("0");
            }
        }
    };

    public HomePage(TApplication application) {
        // The app that hosts other windows
        this.app = application;

        // Window that our app exists in
        this.window = new TWindow(this.app, "Weather Homepage", 65, 30);

        // City Input
        this.cityInputField = new TField(this.window, 0, 0, 30, false, "Enter a City", inputAction);

        // Labels
        this.temperatureLabel = new TLabel(this.window, "Current Temp: ", label_x_offset, label_y_offset + 0);
        this.precipitationLabel = new TLabel(this.window, "Past Hour Precipitation: ", label_x_offset,
                label_y_offset + 1);
        this.pressureLabel = new TLabel(this.window, "Current Pressure: ", label_x_offset, label_y_offset + 2);
        this.humidityLabel = new TLabel(this.window, "Current Humidity: ", label_x_offset, label_y_offset + 3);
        this.windLabel = new TLabel(this.window, "Current Wind: ", label_x_offset, label_y_offset + 4);

        // Progress bar
        this.progressBar = new TProgressBar(this.window, label_x_offset, label_y_offset + 5, 30, 0);

        // Debug text output
        this.debugText = new TText(this.window, "Debug Text", label_x_offset, label_y_offset + 6, 30, 20);
    }

    private Integer updateWeatherLabels(String location) {
        // Fetch city location
        List<Double> cords = Geocode.getLatLong(location);
        Double lat = cords.get(0);
        Double lon = cords.get(1);

        // Fetch weather for location
        WeatherData myD = new WeatherData(lat, lon);
        Map<String, String> weatherdata = myD.getCurrentWeatherData();
        Map<String, String> dailyData = myD.getDailyWeatherData();
        // Update labels with data
        temperatureLabel.setLabel("Current Temp: " + weatherdata.get("temperature_2m") + "°C");
        precipitationLabel.setLabel("Past Hour Precipitation: " + weatherdata.get("precipitation") + "mm");
        pressureLabel.setLabel("Current Pressure: " + weatherdata.get("surface_pressure") + "mmHg");
        humidityLabel.setLabel("Current Humidity: " + weatherdata.get("relative_humidity_2m") + "%");
        windLabel.setLabel("Current Wind: " + weatherdata.get("wind_speed_10m") + "km/h");

        // Stop the progress bar from making updates
        this.progressBarExecutor.shutdown();
        // Jump progress bar to 100%
        this.progressBar.setValue(100);

        // View api map in the debug window
        this.debugText.setText(weatherdata.toString());

        // TODO: Possibly implement scaling
        getWeatherImage(weatherdata.get("weather_code"));
        this.testImage.setScaleType(TImage.Scale.SCALE);

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

    // http://openweathermap.org/img/wn/01d@2x.png
    private BufferedImage downloadImage(String url) {
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            BufferedImage image = ImageIO.read(connection.getInputStream());
            connection.disconnect();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            if (connection != null) {
                connection.disconnect();
            }
            return null;
        }
    }

    private void getWeatherImage(String WMOCode) {
        JSONParser parser = new JSONParser();
        try {
            // This is god awful but it works, need to pick a different JSON library or
            // something
            InputStream is = getClass().getClassLoader().getResourceAsStream("weatherDescriptions.json");
            JSONObject json = (JSONObject) parser.parse(new InputStreamReader(is,
                    "UTF-8"));

            JSONObject codeObject = (JSONObject) json.get(WMOCode);
            JSONObject imageAndDescriptor = (JSONObject) codeObject.get("day");

            BufferedImage image = ImageIO.read(new URL(imageAndDescriptor.get("image").toString()));
            if (image != null) {
                if (this.testImage != null) {
                    this.testImage.remove();
                }
                try {
                    this.testImage = new TImage(this.window, 31, 1, 10, 5, image, 0, 0);
                    // TODO: This breaks everything, maybe add a delay or something prior to
                    // rescaling the image?
                    // this.testImage.setScaleType(TImage.Scale.SCALE);
                } catch (Exception e) {
                    e.printStackTrace();
                    debugText.setText(e.getLocalizedMessage());
                }

            } else {
                debugText.setText(imageAndDescriptor.get("image").toString());
            }

        } catch (Exception e) {
            debugText.setText("There was an error: " + e.getLocalizedMessage());
        }
    }
}
