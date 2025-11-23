package org.weather_checker.Model;

import java.io.File;
import java.time.ZoneId;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.nio.file.attribute.FileTime;
import org.weather_checker.Config.AppConfig;



/**
 * The model responsible for working with the cache
 */
public class Cache {
    private File file;

    public Cache() throws Throwable {
        Files.createDirectories(Paths.get(AppConfig.getProperty("weather.cache.nameDir")));
    }

    public File getFile() { return file; }
    public void setFile(File file) { this.file = file; }

    /**
     * Get the path to the directory with the cache
     *
     * @return Path to the directory
     */
    public Path getPathDir() { return Paths.get(AppConfig.getProperty("weather.cache.nameDir")); }

    /**
     * Checks the cache's relevance;
     * if the last cache update is more than a day old, the cache is considered outdated.
     *
     * @param file The file that stores the cache
     * @return Returns true if the last cache update was more than 24 hours ago.
     * @throws IOException If the file is not found, it will return an error.
     */
    public static boolean checkActualFile(File file) throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(file.toPath());
        ZonedDateTime fileModifiedTime = lastModifiedTime.toInstant().atZone(ZoneId.systemDefault());

        return fileModifiedTime.toLocalDate().equals(LocalDate.now());
    }

    /**
     * Does the cache have any information?
     *
     * @param cacheFile The file that stores the cache
     * @return Returns true if there are any entries in the file.
     * @throws IOException Returns an error if the file does not exist.
     */
    public static boolean hasContent(File cacheFile) throws IOException {
        return Files.size(cacheFile.getAbsoluteFile().toPath()) <= 0;
    }

    /**
     * Get the file storing the cache; if the file does not exist, create it.
     *
     * @param cache Cache object
     * @return Returns the file storing the cache
     * @throws Throwable If an error occurs while creating a file, we display it.
     */
    public File getCacheFile(Cache cache) throws Throwable {
        cache.setFile(new File(cache.getPathDir() + "/" + AppConfig.getProperty("weather.cache.nameFile") + "." + AppConfig.getProperty("weather.cache.extensionFile")));

        if ( !cache.getFile().exists() ) {
            try {
                if (cache.getFile().createNewFile()) System.out.println("Create new cache file!");
            }
            catch (IOException exp) {
                System.out.println(exp.getMessage());
                throw exp.fillInStackTrace();
            }
        }

        return cache.getFile();
    }
}
