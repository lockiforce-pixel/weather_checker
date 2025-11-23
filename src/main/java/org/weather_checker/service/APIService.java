package org.weather_checker.service;

import java.net.URI;
import java.time.Duration;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import org.weather_checker.Config.AppConfig;
import org.weather_checker.HttpController.HTTPController;



/**
 * API support service
 */
public class APIService {

    /**
     * Creating a http client
     *
     * @return Http client for send http request
     */
    public static HttpClient createClient() {

        return HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
    }

    /**
     * Creating a http request for send him to API
     *
     * @return Http request for send http request
     */
    public static HttpRequest createRequest() {

        String url = String.join("", AppConfig.getApiUrl()
                        + AppConfig.getProperty("weather.default.city") + "?",
                HTTPController.convertParamUrlToString());

        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(5))
                .header("Content-Type", "application/json")
                .header("User-Agent", "Mozilla/5.0")
                .GET()
                .build();
    }
}
