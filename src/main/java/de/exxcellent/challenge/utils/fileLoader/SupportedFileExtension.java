package de.exxcellent.challenge.utils.fileLoader;

import de.exxcellent.challenge.utils.fileLoader.impl.CSVFileLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * This enum represents the registry for the supported files
 * by the FileLoader.java
 *
 * To register new extensions, you just need to add new option to the enum
 * and define the handler.
 *
 * for example:
 *  JSON (".json", JSONFileLoader.class)
 */
public enum SupportedFileExtension {

    CSV(".csv", CSVFileLoader.class);

    final String extension;
    final Class<? extends FileLoaderAbstract<?>> handler;

    SupportedFileExtension(String extension, Class<? extends FileLoaderAbstract<?>> clazz) {
        this.extension = extension;
        this.handler = clazz;
    }

    /**
     *
     * @param extension: represents the
     * @return the enum value the represents the extension.
     * if the method returns null, this means that the extension is not registered.
     *
     * for example:
     *  SupportedFileExtension.of(".csv") returns SupportedFileExtension.CSV
     */
    static SupportedFileExtension of(String extension) {
        if (extension == null)
            throw new RuntimeException("extension can not be null!");
        String loweredCaseExtension = extension.toLowerCase();
        return Arrays.stream(SupportedFileExtension.values())
                .filter(supportedExtension -> supportedExtension.extension.equals(loweredCaseExtension))
                .findFirst()
                .orElse(null);
    }

    public FileLoaderAbstract<?> getLoader() {
        try {
            Constructor<? extends FileLoaderAbstract<?>> defaultConstructor = handler.getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            return defaultConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
