package com.mhasancmt.restapp.service;

import com.mhasancmt.restapp.model.WeatherReportModel;

import java.util.List;

public interface WeatherDataService {
    public String getCityId(String cityName, WeatherDataServiceImpl.VolleyResponseListener volleyResponseListener);
    public void getCityForecastById(String cityId, WeatherDataServiceImpl.ForecastByCityIdResponseListener forecast);
    public void getCityForecastByName(String cityName, WeatherDataServiceImpl.getCityForecastByNameResponse getCityForecastByNameResponse);
}
