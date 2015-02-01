package com.nprogramming.weather.data;

import com.nprogramming.weather.WeatherData;

import java.io.IOException;

public interface WeatherRepository {
    
    WeatherData queryByCityName(String cityName) throws IOException;
}
