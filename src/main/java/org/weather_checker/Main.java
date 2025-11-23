package org.weather_checker;

import java.io.File;
import java.nio.file.Files;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.weather_checker.service.*;
import org.weather_checker.HttpController.HTTPController;
import org.weather_checker.Model.Cache;
import org.weather_checker.Model.Weather;



public class Main {

    public static void main(String[] args) throws Throwable {

        File cacheFile = new Cache().getCacheFile(new Cache());

        if (Cache.hasContent(cacheFile) || !Cache.checkActualFile(cacheFile)) {

            HttpClient httpClient = APIService.createClient();
            HttpRequest httpRequest = APIService.createRequest();

            HttpResponse<String> response = HTTPController.sendRequest(httpClient, httpRequest);
            Files.writeString(cacheFile.toPath(), response.body());
        }

        Weather weather = JSONService.getWeatherObj(Files.readString(cacheFile.toPath()));
        ConsoleService.printWeatherInfo(WeatherService.createOutputText(weather));
    }
}