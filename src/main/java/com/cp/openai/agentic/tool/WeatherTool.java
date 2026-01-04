package com.cp.openai.agentic.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Weather tool that the AI agent can use to get weather information
 * (Mock implementation - in real scenario, this would call a weather API)
 */
@Component
public class WeatherTool {

    private final Map<String, WeatherData> weatherDatabase = new HashMap<>();

    public WeatherTool() {
        // Initialize with some mock data
        weatherDatabase.put("new york", new WeatherData("New York", 22.5, "Sunny", 65));
        weatherDatabase.put("london", new WeatherData("London", 15.3, "Cloudy", 78));
        weatherDatabase.put("tokyo", new WeatherData("Tokyo", 28.1, "Partly Cloudy", 55));
        weatherDatabase.put("paris", new WeatherData("Paris", 18.7, "Rainy", 82));
    }

    @Tool(description = "Get current weather information for a given city")
    public WeatherData getWeather(@ToolParam(description = "Name of the city") String city) {
        String cityLower = city.toLowerCase();
        WeatherData data = weatherDatabase.get(cityLower);
        
        if (data == null) {
            // Return default data for unknown cities
            return new WeatherData(city, 20.0, "Unknown", 50);
        }
        
        return data;
    }

    public static class WeatherData {
        private final String city;
        private final double temperature;
        private final String condition;
        private final int humidity;

        public WeatherData(String city, double temperature, String condition, int humidity) {
            this.city = city;
            this.temperature = temperature;
            this.condition = condition;
            this.humidity = humidity;
        }

        public String getCity() {
            return city;
        }

        public double getTemperature() {
            return temperature;
        }

        public String getCondition() {
            return condition;
        }

        public int getHumidity() {
            return humidity;
        }

        @Override
        public String toString() {
            return String.format("Weather in %s: %.1f°C, %s, Humidity: %d%%", 
                city, temperature, condition, humidity);
        }
    }
}

