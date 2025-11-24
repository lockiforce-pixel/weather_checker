package org.weather_checker;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.weather_checker.service.*;
import org.weather_checker.HttpController.HTTPController;
import org.weather_checker.Model.Cache;
import org.weather_checker.Model.Weather;



public class Main {

    public static void main(String[] args) throws Throwable {

        Cache cache = new Cache();

        if (!cache.hasContent() || !cache.checkActualFile()) {
            HttpClient httpClient = APIService.createClient();
            HttpRequest httpRequest = APIService.createRequest();

            HttpResponse<String> response = HTTPController.sendRequest(httpClient, httpRequest);
            cache.storeCache(response.body());
        }

        Weather weather = JSONService.getWeatherObj(cache.getCache());
        ConsoleService.printWeatherInfo(WeatherService.createOutputText(weather));
    }
}