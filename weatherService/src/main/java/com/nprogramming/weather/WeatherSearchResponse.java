package com.nprogramming.weather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherSearchResponse {
    
    private String city;
    private double temperature;

    public WeatherSearchResponse() {
        // Jackson deserialization
    }

    public WeatherSearchResponse(String city, double temperature) {
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
