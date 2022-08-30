package de.exxcellent.challenge;

import de.exxcellent.challenge.utils.fileLoader.FileLoaderManager;
import de.exxcellent.challenge.utils.fileLoader.IFileLoader;
import de.exxcellent.challenge.utils.fileLoader.impl.CSVFileLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileLoaderTest {

    @Test
    public void loadAValidFileShouldSucceed() {
        IFileLoader<?> loader = FileLoaderManager.load("de/exxcellent/challenge/weather.csv");
        Assertions.assertNotNull(loader);
        Assertions.assertTrue(CSVFileLoader.class.isAssignableFrom(loader.getClass()));

        CSVFileLoader csvFileLoader = (CSVFileLoader) loader;

        Assertions.assertNotNull(csvFileLoader.getHeader());
        Assertions.assertNotNull(csvFileLoader.getEntries());
        Assertions.assertEquals(30, csvFileLoader.getEntries().size());
    }

    @Test
    public void callingFileLoaderWithEmptyStringShouldThrowAnException() {
        Assertions.assertThrows(RuntimeException.class, () -> FileLoaderManager.load(null));
        Assertions.assertThrows(RuntimeException.class, () -> FileLoaderManager.load(" "));
    }

    @Test
    public void callingFileLoaderManagerWithAPathWithoutExtensionShouldThrowAnException() {
        Assertions.assertThrows(RuntimeException.class, () -> FileLoaderManager.load("file"));
    }

    @Test
    public void callingFileLoaderManagerWithAPathWithAnUnsupportedExtensionShouldThrowAnException() {
        Assertions.assertThrows(RuntimeException.class, () -> FileLoaderManager.load("file.unsupported"));
    }

}
