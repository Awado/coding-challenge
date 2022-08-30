package de.exxcellent.challenge;

import de.exxcellent.challenge.service.weather.IWeatherService;
import de.exxcellent.challenge.service.weather.impl.WeatherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeatherServiceTest {

    IWeatherService weatherService;

    @BeforeEach
    public void inject() {
        weatherService = new WeatherServiceImpl();
    }

    @Test
    public void findTeamNameWithTheSmallestGoalsDifferenceShouldSucceed() {
        Assertions.assertEquals("14", weatherService.findDayWithTheSmallestTemperatureSpread());
    }

}
