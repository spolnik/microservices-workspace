package com.nprogramming.weather.data;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nprogramming.weather.WeatherData;
import com.nprogramming.weather.WeatherSearchConfiguration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

import static com.nprogramming.weather.utils.TemperatureHelper.kelvinToCelsius;

public class OpenWeatherMapRepository implements WeatherRepository {

    private final String openWeatherMapUrl;
    private final Client client;
    private final String apiKey;

    public OpenWeatherMapRepository(
            WeatherSearchConfiguration configuration, 
            Client client) {
        
        this.client = client;
        openWeatherMapUrl = configuration.getOpenWeatherMapUrl();
        apiKey = configuration.getApiKey();
    }

    @Override
    public WeatherData queryByCityName(String cityName) throws IOException {

        WebTarget webTarget = client
                .target(openWeatherMapUrl)
                .path("weather")
                .queryParam("q", cityName);
        
        Response response = webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("x-api-key", apiKey)
                .get();

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String responseAsJson = response.readEntity(String.class);

        return weatherData(responseAsJson);
    }

    private WeatherData weatherData(String responseAsJson) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode weatherDataJson = mapper.readTree(responseAsJson);
        
        String city = weatherDataJson.get("name").asText();
        double temperature = weatherDataJson.get("main").get("temp").asDouble();

        return new WeatherData(
                city,
                kelvinToCelsius(temperature)
        );
    }
}
