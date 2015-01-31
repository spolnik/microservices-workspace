package com.nprogramming.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherResponse {
    
    private String city;
    private double temperature;

    public WeatherResponse() {
        // Jackson deserialization
    }

    public WeatherResponse(String city, double temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    @JsonProperty
    public String getCity() {
        return city;
    }

    @JsonProperty
    public double getTemperature() {
        return temperature;
    }
}
