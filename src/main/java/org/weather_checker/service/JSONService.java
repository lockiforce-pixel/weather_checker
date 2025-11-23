package org.weather_checker.service;

import org.weather_checker.Model.Weather;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The service responsible for processing the JSON
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONService {

    /**
     * Convert JSON to Weather object
     *
     * @param json Response body in json format
     * @return A weather object created from the json
     * @throws JsonProcessingException Error creating object (mapper) based on your json
     */
    public static Weather getWeatherObj(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        return mapper.readValue(json, Weather.class);
    }
}
