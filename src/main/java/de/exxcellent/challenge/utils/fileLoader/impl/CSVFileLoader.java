package de.exxcellent.challenge.utils.fileLoader.impl;

import de.exxcellent.challenge.utils.fileLoader.FileLoaderAbstract;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class CSVFileLoader extends FileLoaderAbstract<CSVRecord> {

    private CSVRecord header;
    private List<CSVRecord> records;

    @Override
    protected FileLoaderAbstract<CSVRecord> load(String path) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(path);
        if (stream == null)
            throw new RuntimeException();
        try(InputStreamReader inputStreamReader = new InputStreamReader(stream)) {
            records = CSVParser.parse(inputStreamReader, CSVFormat.DEFAULT)
                    .stream().collect(Collectors.toList());

            if (records.isEmpty())
                throw new RuntimeException("No header row found");

            header = records.remove(0);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse the given csv file: " + path);
        }
        return this;
    }

    @Override
    public List<CSVRecord> getEntries() {return records;}

    public CSVRecord getHeader() {
        return header;
    }

}
