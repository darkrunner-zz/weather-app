package com.jvs.owm.service.model;

public class Statistics {

    public Statistics() {
    }

    private Double maxTemperature;
    private Double minTemperature;
    private Double maxTemperatureCelsius;
    private Double minTemperatureCelsius;

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
        setMaxTemperatureCelsius(maxTemperature - 273.15);
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
        setMinTemperatureCelsius(minTemperature - 273.15);
    }

    public Double getMaxTemperatureCelsius() {
        return maxTemperatureCelsius;
    }

    public void setMaxTemperatureCelsius(Double maxTemperatureCelsius) {
        this.maxTemperatureCelsius = maxTemperatureCelsius;
    }

    public Double getMinTemperatureCelsius() {
        return minTemperatureCelsius;
    }

    public void setMinTemperatureCelsius(Double minTemperatureCelsius) {
        this.minTemperatureCelsius = minTemperatureCelsius;
    }
}
