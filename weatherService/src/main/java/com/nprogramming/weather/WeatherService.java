package com.nprogramming.weather;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class WeatherService extends Application<WeatherServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new WeatherService().run(args);
    }
    
    @Override
    public void run(
            WeatherServiceConfiguration configuration, 
            Environment environment) throws Exception {
        
        final WeatherResource resource = new WeatherResource(
                configuration.getDefaultCity()
        );
        final CityHealthCheck healthCheck = new CityHealthCheck(
            configuration.getDefaultCity()
        );
        
        environment.healthChecks().register("city", healthCheck);
        environment.jersey().register(resource);
    }

    @Override
    public String getName() {
        return "weather-app";
    }
}
