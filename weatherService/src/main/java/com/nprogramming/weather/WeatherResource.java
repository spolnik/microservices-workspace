package com.nprogramming.weather;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Random;

import static com.nprogramming.weather.utils.TemperatureHelper.scaleTemperature;

@Path("/weather")
@Produces(MediaType.APPLICATION_JSON)
public class WeatherResource {

    private final String defaultCity;
    
    private Random temperatureGenerator = new Random();

    public WeatherResource(String defaultCity) {
        this.defaultCity = defaultCity;
    }

    @GET
    @Timed
    public WeatherSearchResponse getWeather(@QueryParam("city") Optional<String> city) {

        return new WeatherSearchResponse(
                city.or(defaultCity),
                scaleTemperature(temperatureGenerator.nextDouble() * 30.0)
        );
    }
}
