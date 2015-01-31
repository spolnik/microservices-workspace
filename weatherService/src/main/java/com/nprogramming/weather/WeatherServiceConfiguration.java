package com.nprogramming.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class WeatherServiceConfiguration extends Configuration {
    
    @NotEmpty
    private String defaultCity = "Cracow";

    @JsonProperty
    public String getDefaultCity() {
        return defaultCity;
    }

    @JsonProperty
    public void setDefaultCity(String city) {
        defaultCity = city;
    }
}
