package com.mhasancmt.restapp.model;

public class WeatherReportModel {

    private int id;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String weather_created;
    private String applicable_date;
    private float weather_min_temp;
    private float weather_max_temp;
    private float weather_the_temp;
    private float weather_wind_speed;
    private float weather_wind_direction;
    private int weather_air_pressure;
    private int weather_humidity;
    private float weather_visibility;
    private String weather_predictability;

    public WeatherReportModel() {
    }

    public WeatherReportModel(int id, String weather_state_name,
                              String weather_state_abbr, String wind_direction_compass,
                              String weather_created, String applicable_date,
                              float weather_min_temp, float weather_max_temp,
                              float weather_the_temp, float weather_wind_speed,
                              float weather_wind_direction, int weather_air_pressure,
                              int weather_humidity, float weather_visibility,
                              String weather_predictability) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.weather_created = weather_created;
        this.applicable_date = applicable_date;
        this.weather_min_temp = weather_min_temp;
        this.weather_max_temp = weather_max_temp;
        this.weather_the_temp = weather_the_temp;
        this.weather_wind_speed = weather_wind_speed;
        this.weather_wind_direction = weather_wind_direction;
        this.weather_air_pressure = weather_air_pressure;
        this.weather_humidity = weather_humidity;
        this.weather_visibility = weather_visibility;
        this.weather_predictability = weather_predictability;
    }

    @Override
    public String toString() {
        return weather_state_name +" Date: " + applicable_date +
                "LO: " + weather_min_temp +
                "Hi: " + weather_max_temp +
                "TEMP: " + weather_the_temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public String getWeather_created() {
        return weather_created;
    }

    public void setWeather_created(String weather_created) {
        this.weather_created = weather_created;
    }

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    public float getWeather_min_temp() {
        return weather_min_temp;
    }

    public void setWeather_min_temp(float weather_min_temp) {
        this.weather_min_temp = weather_min_temp;
    }

    public float getWeather_max_temp() {
        return weather_max_temp;
    }

    public void setWeather_max_temp(float weather_max_temp) {
        this.weather_max_temp = weather_max_temp;
    }

    public float getWeather_the_temp() {
        return weather_the_temp;
    }

    public void setWeather_the_temp(float weather_the_temp) {
        this.weather_the_temp = weather_the_temp;
    }

    public float getWeather_wind_speed() {
        return weather_wind_speed;
    }

    public void setWeather_wind_speed(float weather_wind_speed) {
        this.weather_wind_speed = weather_wind_speed;
    }

    public float getWeather_wind_direction() {
        return weather_wind_direction;
    }

    public void setWeather_wind_direction(float weather_wind_direction) {
        this.weather_wind_direction = weather_wind_direction;
    }

    public int getWeather_air_pressure() {
        return weather_air_pressure;
    }

    public void setWeather_air_pressure(int weather_air_pressure) {
        this.weather_air_pressure = weather_air_pressure;
    }

    public int getWeather_humidity() {
        return weather_humidity;
    }

    public void setWeather_humidity(int weather_humidity) {
        this.weather_humidity = weather_humidity;
    }

    public float getWeather_visibility() {
        return weather_visibility;
    }

    public void setWeather_visibility(float weather_visibility) {
        this.weather_visibility = weather_visibility;
    }

    public String getWeather_predictability() {
        return weather_predictability;
    }

    public void setWeather_predictability(String weather_predictability) {
        this.weather_predictability = weather_predictability;
    }
}
