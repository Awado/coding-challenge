package de.exxcellent.challenge.service.football.impl;

import de.exxcellent.challenge.service.Files;
import de.exxcellent.challenge.service.football.IFootballService;
import de.exxcellent.challenge.service.football.config.FootballHeadersWhiteList;
import de.exxcellent.challenge.utils.fileLoader.impl.CSVFileLoader;
import de.exxcellent.challenge.utils.fileLoader.FileLoaderManager;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class FootballServiceImpl implements IFootballService {
    @Override
    public String findTeamNameWithTheSmallestGoalsDifference() {
        CSVFileLoader csvFileLoader = FileLoaderManager.load(Files.FOOTBALL_FILE.path);

        Map<String, Integer> headerMap = new HashMap<>();
        IntStream.range(0, csvFileLoader.getHeader().size()).forEach(i -> {
            String header = csvFileLoader.getHeader().get(i);
            if (FootballHeadersWhiteList.contains(header))
                headerMap.put(header, i);
        });

        return getTeamNameWithSmallestGoalsDifference(csvFileLoader, headerMap);
    }
    private String getTeamNameWithSmallestGoalsDifference(CSVFileLoader csvFileLoader, Map<String, Integer> headerMap) {
        return csvFileLoader.getEntries()
                .stream()
                .min(Comparator.comparingInt(record -> goalsMinusAllowedGoals(headerMap, record))) // min temp per day
                .map(record -> record.get(headerMap.get(FootballHeadersWhiteList.TEAM.column)))
                .orElse(null);
    }

    private Integer goalsMinusAllowedGoals(Map<String, Integer> headerMap, CSVRecord record) {
        Integer goals = Integer.parseInt(record.get(headerMap.get(FootballHeadersWhiteList.GOALS.column)));
        Integer allowedGoals = Integer.parseInt(record.get(headerMap.get(FootballHeadersWhiteList.GOALS_ALLOWED.column)));

        return Math.abs(goals - allowedGoals);
    }
}
