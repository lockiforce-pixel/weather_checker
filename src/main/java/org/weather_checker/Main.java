package org.weather_checker;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import org.weather_checker.Model.Weather;
import java.util.HashMap;
import org.weather_checker.Worker.CacheWorker;
import org.weather_checker.Worker.HTTPWorker;
import org.weather_checker.Worker.JSONWorker;

import java.util.stream.Collectors;



public class Main {
    public static void main(String[] args) throws Throwable {

        File cache = new org.weather_checker.Worker.CacheWorker().getCacheFile("ResponseBody.json");
        String responseBody;
        if (Files.size(cache.getAbsoluteFile().toPath()) <= 0 || !CacheWorker.checkActualFile(cache)) {

            String urlSite = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/55.0339%2C73.3151?";
            Map<String, String> paramsUrl = new HashMap<>(
                    Map.of(
                            "unitGroup", "us",
                            "key", "K8RVQS9GTKR7EXNHRRDSEMH4A",
                            "contentType", "json"

                    )
            );
            String paramUrlString = paramsUrl.entrySet()
                    .stream()
                    .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                    .collect(Collectors.joining("&"));

            System.out.println("Sending request...");
            responseBody = HTTPWorker.send(String.join("", urlSite, paramUrlString)).body();
            Files.writeString(cache.toPath(), responseBody);
        }
        responseBody = Files.readString(cache.toPath());

        Weather weather = JSONWorker.getString(responseBody);
        Main.printWeatherInfo(weather);
    }

    // TODO: доработать, должна выводится инфа об определенном дне который можно указать в аргумете
    public static void printWeatherInfo(Weather weather) {
        System.out.println("Ваша погода на сегодня:");
        System.out.println("Температура: " + weather.getFirstDay().getTemp('C') + ".C");
        System.out.println("Облачность: " + weather.getFirstDay().getCloudcover());
        System.out.println("Условия: " + weather.getFirstDay().getConditions());
        System.out.println("Дата: " + weather.getFirstDay().getDatetime());
        System.out.println("Описание: " + weather.getFirstDay().getDescription());
        System.out.println("Иконка: " + weather.getFirstDay().getIcon());
        System.out.println("Влажность: " + weather.getFirstDay().getHumidity());
        System.out.println("Фаза луны: " + weather.getFirstDay().getMoonphase());
        System.out.println("Осадок: " + weather.getFirstDay().getPrecipcover());
        System.out.println("Тип осадка: " + weather.getFirstDay().getPreciptype());
        System.out.println("Скорость ветра: " + weather.getFirstDay().getWindspeed());
    }
}