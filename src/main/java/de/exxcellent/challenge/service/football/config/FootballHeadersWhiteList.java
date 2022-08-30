package de.exxcellent.challenge.service.football.config;

import de.exxcellent.challenge.service.weather.config.WeatherHeadersWhiteList;

import java.util.Arrays;

public enum FootballHeadersWhiteList {

    TEAM("Team"),
    GOALS("Goals"),
    GOALS_ALLOWED("Goals Allowed");

    public final String column;

    FootballHeadersWhiteList(String column) {
        this.column = column;
    }

    public static boolean contains(String column) {
        return Arrays.stream(FootballHeadersWhiteList.values()).anyMatch(e -> e.column.equals(column));
    }

}
