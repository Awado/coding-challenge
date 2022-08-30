package de.exxcellent.challenge.utils.fileLoader;

public class FileLoaderManager {

    /**
     * This is the entry point to the file loading framework.
     * based on the extension of the given "valid" file path
     * the load method will detect which file loader should be called
     * for example:
     *      example.csv will call CSVFileLoader
     * @param path the path of the file to load
     * @return the proper FileLoader if the extension is supported.
     * @param <T> The matching FileLoader implementation to the given extension
     */
    public static <T extends IFileLoader<?>> T load(String path) {
        if (path == null || path.isBlank())
            throw new RuntimeException("Path can not be null!");

        int dotIndex = path.lastIndexOf(".");
        if (dotIndex == -1)
            throw new RuntimeException("Invalid file path, no extension found");

        String foundExtension = path.substring(dotIndex);
        SupportedFileExtension supportedFileExtension = SupportedFileExtension.of(foundExtension);
        if (supportedFileExtension == null)
            throw new RuntimeException(String.format("No handler found for extension \"%s\"", foundExtension));

        return (T) supportedFileExtension.getLoader().load(path);
    }

}
