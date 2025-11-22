package org.weather_checker.service;

import org.weather_checker.Config.AppConfig;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class APIService {

    public static HttpResponse<String> sendRequest(HttpClient client, HttpRequest request) throws IOException, InterruptedException {

        System.out.println("Sending request...");

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static String getParamUrlToString() {

        HashMap<String, String> paramUrlMap = new HashMap<>(
                Map.of(
                        "unitGroup", AppConfig.getUnitGroup(),
                        "key", AppConfig.getApiKey(),
                        "contentType", AppConfig.getProperty("weather.api.contentType")

                )
        );

        return paramUrlMap.entrySet()
                .stream()
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("&"));
    }
}
