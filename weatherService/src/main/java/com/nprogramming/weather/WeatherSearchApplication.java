package com.nprogramming.weather;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WeatherSearchApplication extends Application<WeatherSearchConfiguration> {

    public static void main(String[] args) throws Exception {
        new WeatherSearchApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<WeatherSearchConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets", "/app", "index.html", "assets"));
    }

    @Override
    public void run(
            WeatherSearchConfiguration configuration,
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
