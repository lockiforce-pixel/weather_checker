package org.weather_checker.Model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.weather_checker.Config.AppConfig;



public class Cache {
    private File file;

    public Cache() throws Throwable {

        Files.createDirectories(Paths.get(AppConfig.getProperty("weather.cache.nameDir")));
    }

    public File getFile() { return file; }
    public void setFile(File file) { this.file = file; }

    public Path getPathCacheDir() { return Paths.get(AppConfig.getProperty("weather.cache.nameDir")); }
}
