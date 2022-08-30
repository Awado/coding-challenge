package de.exxcellent.challenge.service.weather.impl;

import de.exxcellent.challenge.service.weather.IWeatherService;
import de.exxcellent.challenge.service.weather.config.WeatherHeadersWhiteList;
import de.exxcellent.challenge.utils.fileLoader.impl.CSVFileLoader;
import de.exxcellent.challenge.utils.fileLoader.FileLoaderManager;
import org.apache.commons.csv.CSVRecord;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class WeatherServiceImpl implements IWeatherService {

    @Override
    public String findDayWithTheSmallestTemperatureSpread() {
        CSVFileLoader csvFileLoader = FileLoaderManager.load("de/exxcellent/challenge/weather.csv");

        Map<String, Integer> headerMap = new HashMap<>();
        IntStream.range(0, csvFileLoader.getHeader().size()).forEach(i -> {
            String header = csvFileLoader.getHeader().get(i);
            if (WeatherHeadersWhiteList.contains(header))
                headerMap.put(header, i);
        });

        return getDayWithMinSpread(csvFileLoader, headerMap);
    }

    private String getDayWithMinSpread(CSVFileLoader csvFileLoader, Map<String, Integer> headerMap) {
        return csvFileLoader.getEntries()
                .stream()
                .min(Comparator.comparingInt(record -> maxTempMinusMinTemp(headerMap, record)))
                .map(record -> record.get(headerMap.get(WeatherHeadersWhiteList.DAY.column)))
                .orElse(null);
    }

    private Integer maxTempMinusMinTemp(Map<String, Integer> headerMap, CSVRecord record) {
        Integer maxTemp = Integer.parseInt(record.get(headerMap.get(WeatherHeadersWhiteList.MAX_TEMP.column)));
        Integer minTemp = Integer.parseInt(record.get(headerMap.get(WeatherHeadersWhiteList.MIN_TEMP.column)));

        return Math.abs(maxTemp - minTemp);
    }
}
