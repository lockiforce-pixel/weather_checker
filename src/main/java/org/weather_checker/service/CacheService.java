package org.weather_checker.service;

import java.io.File;
import java.time.ZoneId;
import java.time.Instant;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.nio.file.attribute.FileTime;
import org.weather_checker.Model.Cache;
import org.weather_checker.Config.AppConfig;



public class CacheService {


    public static boolean checkActualFile(File file) throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(file.toPath());
        Instant instant = lastModifiedTime.toInstant();
        ZonedDateTime fileModifiedTime = instant.atZone(ZoneId.systemDefault());
        LocalDate fileModifiedDate = fileModifiedTime.toLocalDate();

        return fileModifiedDate.equals(LocalDate.now());
    }

    public static boolean hasContent(File cacheFile) throws IOException {
        return Files.size(cacheFile.getAbsoluteFile().toPath()) <= 0;
    }

    public File getCacheFile(Cache cache) throws Throwable {
        cache.setFile(new File(cache.getPathCacheDir() + "/" + AppConfig.getProperty("weather.cache.nameFile") + "." + AppConfig.getProperty("weather.cache.extensionFile")));

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
