package de.exxcellent.challenge.service;

public enum Files {
    WEATHER_FILE("de/exxcellent/challenge/weather.csv"),
    FOOTBALL_FILE("de/exxcellent/challenge/football.csv");

    public final String path;

    Files(String path) {
        this.path = path;
    }

}
