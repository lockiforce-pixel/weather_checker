package org.weather_checker.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Weather conditions model
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    private String timezone;
    private String description;
    private Day[] days;

    /**
     * All getters and setters must be for create Mapper JSON form response
     */

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }

    public void setDays(Day[] days) { this.days = days; }
    public Day[] getDays() { return this.days; }

    public Day getFirstDay() { return this.days[0]; }
}
