package org.weather_checker.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.weather_checker.Model.Weather;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JSONService {

    public static Weather getString(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        return mapper.readValue(json, Weather.class);
    }
}
