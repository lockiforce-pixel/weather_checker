package org.weather_checker.HttpController;

import org.weather_checker.Config.AppConfig;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

/**
 * Controller for working with HTTP requests
 */
public class HTTPController {

    /**
     * Sending a request to the server
     *
     * @param client Client of our HTTP request
     * @param request Request our HTTP request
     * @return Returns the http response
     * @throws IOException Returns an error when sending a http request.
     * @throws InterruptedException If the request is waiting for a long time for a response, it will stop and generate an error.
     */
    public static HttpResponse<String> sendRequest(HttpClient client, HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    /**
     * Converts URL parameters to a string
     *
     * @return Parameters in string format
     */
    public static String convertParamUrlToString() {

        return getParamUrl().entrySet()
                .stream()
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(Collectors.joining("&"));
    }

    /**
     *  Parameters are taken from the app config!
     *
     * @return Returns URL parameters in hashmap format.
     */
    public static HashMap<String, String> getParamUrl() {
        return new HashMap<>(
                Map.of("unitGroup", AppConfig.getUnitGroup(),
                        "key", AppConfig.getApiKey(),
                        "contentType", AppConfig.getProperty("weather.api.contentType"))
        );
    }
}
