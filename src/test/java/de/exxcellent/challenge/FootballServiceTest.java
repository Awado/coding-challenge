package de.exxcellent.challenge;

import de.exxcellent.challenge.service.football.IFootballService;
import de.exxcellent.challenge.service.football.impl.FootballServiceImpl;
import de.exxcellent.challenge.service.weather.impl.WeatherServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FootballServiceTest {

    IFootballService footballService;

    @BeforeEach
    public void inject() {
        footballService = new FootballServiceImpl();
    }

    @Test
    public void findTeamNameWithTheSmallestGoalsDifferenceShouldSucceed() {
        Assertions.assertEquals("Aston_Villa", footballService.findTeamNameWithTheSmallestGoalsDifference());
    }

}
