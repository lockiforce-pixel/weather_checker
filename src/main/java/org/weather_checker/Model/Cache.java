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
    private final File cacheDir;

    public Cache() throws Throwable {
        this.cacheDir = Files.createDirectories(Paths.get(AppConfig.getProperty("weather.cache.nameDir"))).toFile();
    }

    /**
     * Store response to cache
     *
     * @param body Response body in String format
     * @throws IOException If an error occurs during recording
     */
    public void storeCache(String body) throws IOException {
        if (!this.getPath().toFile().exists()) {
            this.createCacheFile();
        }
        Files.writeString(this.getPath(), body);
    }

    private void createCacheFile() throws IOException {
        Files.createFile(getPath());
    }

    /**
     * Get the path to the directory with the cache
     *
     * @return Path to the directory
     */
    public Path getPath() {
        return Paths.get(AppConfig.getProperty("weather.cache.nameDir") + "/"
                + AppConfig.getProperty("weather.cache.nameFile")
                + "." + AppConfig.getProperty("weather.cache.extensionFile"));
    }


    /**
     * Checks the cache's relevance;
     * if the last cache update is more than a day old, the cache is considered outdated.
     *
     * @return Returns true if the last cache update was more than 24 hours ago.
     * @throws IOException If the file is not found, it will return an error.
     */
    public boolean checkActualFile() throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(this.cacheDir.toPath());
        ZonedDateTime fileModifiedTime = lastModifiedTime.toInstant().atZone(ZoneId.systemDefault());

        return fileModifiedTime.toLocalDate().equals(LocalDate.now());
    }

    /**
     * Does the cache have any information?
     *
     * @return Returns true if there are any entries in the file.
     * @throws IOException Returns an error if the file does not exist.
     */
    public boolean isEmpty() throws IOException {
        if (!Files.exists(this.getPath())) {
            this.createCacheFile();
        }
        return Files.readString(this.getPath()).isEmpty();
    }

    public String getCache() throws IOException {
        return Files.readString(getPath());
    }
}
