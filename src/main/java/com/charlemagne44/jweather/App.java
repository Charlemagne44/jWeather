package com.charlemagne44.jweather;

import com.charlemagne44.jweather.frontend.HomePage;

import jexer.TApplication;

public class App {
    public static void main(String[] args) throws Exception {

        TApplication app = new TApplication(TApplication.BackendType.XTERM);
        app.addToolMenu();
        app.addFileMenu();
        app.addWindowMenu();

        HomePage myHomePage = new HomePage(app);

        app.run();
    }
}
