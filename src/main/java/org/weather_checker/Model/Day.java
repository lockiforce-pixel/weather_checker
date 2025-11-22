package org.weather_checker.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    private String datetime;
    private Double temp;
    private Double humidity;
    private Double precipcover;
    private ArrayList<String> preciptype;
    private Double windspeed;
    private Double cloudcover;
    private Double moonphase;
    private String conditions;
    private String description;
    private String icon;

    /**
     * Getters Setters for params
     * @param datetime
     */

    public void setDatetime(String datetime) { this.datetime = datetime; }
    public String getDatetime() { return this.datetime; }

    public void setTemp(Double temp) { this.temp = temp; }
    public Long getTemp(char type) {
        if (type == 'F') {
            return Math.round(this.temp);
        } else if (type == 'C') {
            return Math.round((this.temp - 32) * 5/9);
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setHumidity(Double humidity) { this.humidity = humidity; }
    public Double getHumidity() { return this.humidity; }

    public void setPrecipcover(Double precipcover) { this.precipcover = precipcover; }
    public Double getPrecipcover() { return this.precipcover; }

    public void setPreciptype(ArrayList<String> preciptype) { this.preciptype = preciptype; }
    public ArrayList<String> getPreciptype() { return this.preciptype; }

    public void setWindspeed(Double windspeed) { this.windspeed = windspeed; }
    public Double getWindspeed() { return this.windspeed; }

    public void setCloudcover(Double cloudcover) { this.cloudcover = cloudcover; }
    public Double getCloudcover() { return this.cloudcover; }

    public Double getMoonphase() { return moonphase; }
    public void setMoonphase(Double moonphase) { this.moonphase = moonphase; }

    public String getConditions() { return conditions; }
    public void setConditions(String conditions) { this.conditions = conditions; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}
