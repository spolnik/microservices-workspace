package com.nprogramming.weather.resources

import com.google.common.base.Optional
import com.nprogramming.weather.WeatherResource
import spock.lang.Specification

class WeatherResourceTest extends Specification {

    def "should return temperature for Cracow"() {
        given: "instance of resource created"
        def resource = new WeatherResource("Cracow");

        expect: "it returns the proper message"
        resource.getWeather(Optional.absent()) &&
                resource.getWeather(Optional.absent()).city == "Cracow" &&
                resource.getWeather(Optional.absent()).temperature > -50 &&
                resource.getWeather(Optional.absent()).temperature < 50
    }
}
