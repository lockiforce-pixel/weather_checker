package org.weather_checker.Model;

import org.weather_checker.service.APIService;
import org.weather_checker.Config.AppConfig;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

public class HTTP {

    public static HttpClient createClient() {

        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    public static HttpRequest createRequest() {

        String url = String.join("", AppConfig.getApiUrl()
                + AppConfig.getProperty("weather.default.city") + "?",
                APIService.getParamUrlToString());

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();
    }
}
