package org.weather_checker.Config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;



public class AppConfig {

    private static final Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.configure()
            .directory(".")
            .ignoreIfMalformed().load();


    static {
        try (InputStream input = AppConfig.class.getClassLoader()
                .getResourceAsStream("application.properties"))
        {
            if (input != null) {
                properties.load(input);
            }
            else {
                throw new RuntimeException("Unable to find application.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration", e);
        }
    }

    public static String getProperty(String key) {

        // Support vars environment (env, var)
        String value = System.getenv(key.replace(".", "_").toUpperCase());
        return Objects.isNull(value) ? properties.getProperty(key) : value;
    }

    /**
     *  Specific getters for convenience
     */

    public static String getApiUrl() {
        return getProperty("weather.api.url");
    }

    public static String getApiKey() { return dotenv.get("WEATHER_API_KEY"); }

    public static String getUnitGroup() {
        return getProperty("weather.api.unitGroup");
    }
}
