package org.weather_checker.service;

import org.weather_checker.Model.Weather;


/**
 * A service that is responsible for processing weather data
 */
public class WeatherService {

    /**
     * Create text for output to console
     *
     * @param weather Weather object with data temp, date and etc
     * @return Return text for output to console of String type
     */
    public static String createOutputText(Weather weather) {

        return "Ваша погода на сегодня: \n" +
                "Температура: " + new WeatherService().getTempCelsius(
                        weather.getDays()[0].getTemp()) + "°C" + "\n"
                + "Облачность: " + weather.getFirstDay().getCloudcover() + "\n"
                + "Условия: " + weather.getFirstDay().getConditions() + "\n"
                + "Дата: " + weather.getFirstDay().getDatetime() + "\n"
                + "Описание: " + weather.getFirstDay().getDescription() + "\n"
                + "Иконка: " + weather.getFirstDay().getIcon() + "\n"
                + "Влажность: " + weather.getFirstDay().getHumidity() + "\n"
                + "Фаза луны: " + weather.getFirstDay().getMoonphase() + "\n"
                + "Осадок: " + weather.getFirstDay().getPrecipcover() + "\n"
                + "Тип осадка: " + weather.getFirstDay().getPreciptype() + "\n"
                + "Скорость ветра: " + weather.getFirstDay().getWindspeed() + "\n";
    }


    /**
     * Convert Fahrenheit to Celsius
     *
     * @param temp The meaning of temperature. Double type
     * @return Celsius value. Long type
     */
    public Long getTempCelsius(Double temp) {
            return Math.round((temp - 32) * 5/9);
    }
}
