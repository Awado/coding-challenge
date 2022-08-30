package de.exxcellent.challenge.utils.fileLoader;

import java.util.List;

/**
 * the interface of a fileLoader
 * @param <T> is the object the represents a record (for example CSVRecord in a CSV file)
 */
public interface IFileLoader<T> {
    List<T> getEntries();

}
