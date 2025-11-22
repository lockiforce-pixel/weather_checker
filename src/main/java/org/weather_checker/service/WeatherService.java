package org.weather_checker.service;

public class WeatherService {
    
    public Long getTempCelsius(Double temp, char type) {
        if (type == 'F') {
            return Math.round(temp);
        } else if (type == 'C') {
            return Math.round((temp - 32) * 5/9);
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}
