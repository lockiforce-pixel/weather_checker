package org.weather_checker.service;

import org.weather_checker.Model.Weather;

public class ConsoleService {

    public static void printWeatherInfo(Weather weather) {
        System.out.println("Ваша погода на сегодня:");
        System.out.println("Температура: " + new WeatherService().getTempCelsius(weather.getDays()[0].getTemp(), 'C') + "°C");
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
