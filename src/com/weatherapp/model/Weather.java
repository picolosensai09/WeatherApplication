package com.weatherapp.model;

public class Weather {
    private String cityName;
    private String country;
    private double temperature;
    private double feelsLike;
    private int humidity;
    private int pressure;
    private String description;
    private String mainCondition;
    private double windSpeed;
    private int visibility;
    
    public Weather() {}
    
    public Weather(String cityName, String country, double temperature, 
                  double feelsLike, int humidity, int pressure, 
                  String description, String mainCondition, 
                  double windSpeed, int visibility) {
        this.cityName = cityName;
        this.country = country;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.mainCondition = mainCondition;
        this.windSpeed = windSpeed;
        this.visibility = visibility;
    }
    
    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    
    public double getFeelsLike() {
        return feelsLike;
    }
    
    public void setFeelsLike(double feelsLike) {
        this.feelsLike = feelsLike;
    }
    
    public int getHumidity() {
        return humidity;
    }
    
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    
    public int getPressure() {
        return pressure;
    }
    
    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getMainCondition() {
        return mainCondition;
    }
    
    public void setMainCondition(String mainCondition) {
        this.mainCondition = mainCondition;
    }
    
    public double getWindSpeed() {
        return windSpeed;
    }
    
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    public int getVisibility() {
        return visibility;
    }
    
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
    
    @Override
    public String toString() {
        return String.format(
            "Weather{cityName='%s', country='%s', temperature=%.1f°C, " +
            "humidity=%d%%, description='%s'}", 
            cityName, country, temperature, humidity, description
        );
    }
}
