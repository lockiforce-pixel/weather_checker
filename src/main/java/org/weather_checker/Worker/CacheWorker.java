package org.weather_checker.Worker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.*;

public class CacheWorker {
    private final String NAME_CACHE_DIR = "cache";
    private Path pathCacheDir;
    private File file;

    public CacheWorker() throws Throwable {

        String baseDir = System.getProperty("app.cache.dir", System.getProperty("user.dir") + "/" + this.NAME_CACHE_DIR);
        this.pathCacheDir = Paths.get(baseDir);
        Files.createDirectories(this.pathCacheDir);


    }

    public static boolean checkActualFile(File file) throws IOException {
        FileTime lastModifiedTime = Files.getLastModifiedTime(file.toPath());
        Instant instant = lastModifiedTime.toInstant();
        ZonedDateTime fileModifiedTime = instant.atZone(ZoneId.systemDefault());
        LocalDate fileModifiedDate = fileModifiedTime.toLocalDate();

        return fileModifiedDate.equals(LocalDate.now());
    }

    public File getCacheFile(String name) throws Throwable {
        this.file = new File(pathCacheDir + "/" + name);

        if ( !this.file.exists() ) {
            try {
                if (this.file.createNewFile()) System.out.println("Create new cache file!");
            }
            catch (IOException exp) {
                System.out.println(exp.getMessage());
                throw exp.fillInStackTrace();
            }
        }

        return this.file;
    }
}
