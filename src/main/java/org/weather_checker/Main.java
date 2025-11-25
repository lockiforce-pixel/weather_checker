package org.weather_checker;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.weather_checker.service.*;
import org.weather_checker.HttpController.HTTPController;
import org.weather_checker.Model.Cache;
import org.weather_checker.Model.Weather;



public class Main {

    public static void main(String[] args) throws Throwable {

        Cache cache = new Cache();

        if (cache.isEmpty() || !cache.checkActualFile()) {
            HttpClient httpClient = APIService.createClient();
            HttpRequest httpRequest = APIService.createRequest();

            CompletableFuture<HttpResponse<String>> asyncRequest = CompletableFuture.supplyAsync(() -> {
                try {
                    return HTTPController.sendRequest(httpClient, httpRequest);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            cache.storeCache(asyncRequest.get().body());
        }

        Weather weather = JSONService.getWeatherObj(cache.getCache());
        ConsoleService.printWeatherInfo(WeatherService.createOutputText(weather));
    }
}