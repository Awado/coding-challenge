package de.exxcellent.challenge.utils.fileLoader;

public abstract class FileLoaderAbstract<T> implements IFileLoader<T> {

    protected abstract FileLoaderAbstract<T> load(String path);


}
