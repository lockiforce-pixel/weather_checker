package org.weather_checker;

import java.io.File;
import java.nio.file.Files;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.weather_checker.service.*;
import org.weather_checker.Model.HTTP;
import org.weather_checker.Model.Cache;
import org.weather_checker.Model.Weather;



public class Main {

    public static void main(String[] args) throws Throwable {
        System.out.println(System.getProperty("user.dir"));
        File cacheFile = new CacheService().getCacheFile(new Cache());

        if (CacheService.hasContent(cacheFile) || !CacheService.checkActualFile(cacheFile)) {

            HttpClient httpClient = HTTP.createClient();
            HttpRequest httpRequest = HTTP.createRequest();

            HttpResponse<String> response = APIService.sendRequest(httpClient, httpRequest);
            Files.writeString(cacheFile.toPath(), response.body());
        }

        Weather weather = JSONService.getString(Files.readString(cacheFile.toPath()));
        ConsoleService.printWeatherInfo(weather);
    }
}