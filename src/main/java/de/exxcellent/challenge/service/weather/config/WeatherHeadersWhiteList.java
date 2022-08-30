package de.exxcellent.challenge.service.weather.config;

import java.util.Arrays;

public enum WeatherHeadersWhiteList {
    DAY("Day"),
    MAX_TEMP("MxT"),
    MIN_TEMP("MnT");

    public final String column;

    WeatherHeadersWhiteList(String column) {
        this.column = column;
    }

    public static boolean contains(String column) {
        return Arrays.stream(WeatherHeadersWhiteList.values()).anyMatch(e -> e.column.equals(column));
    }
}
