package com.nprogramming.weather.utils;

import java.math.BigDecimal;

public final class TemperatureHelper {
    
    public static double scaleTemperature(double value) {

        return new BigDecimal(value)
                .setScale(2, BigDecimal.ROUND_HALF_EVEN)
                .doubleValue();
    }
}
