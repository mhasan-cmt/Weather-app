package com.mhasancmt.restapp.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mhasancmt.restapp.MySingleton;
import com.mhasancmt.restapp.model.WeatherReportModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataServiceImpl implements WeatherDataService {
    Context context;
    String cityId;

    public WeatherDataServiceImpl(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String msg);

        void onResponse(String cityId);
    }

    public interface ForecastByCityIdResponseListener {
        void onError(String msg);

        void onResponse(List<WeatherReportModel> weatherReportModels);
    }

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";

    @Override
    public String getCityId(String cityName, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_CITY_ID + cityName;
        JsonArrayRequest jsonArrayRequest
                = new JsonArrayRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String cityId = "";
                try {
                    JSONObject cityInfo = response.getJSONObject(0);
                    cityId = cityInfo.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(context,"City Id: "+cityId, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(cityId);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "Somthing wrong.", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something went wrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        return cityId;
    }

    @Override
    public void getCityForecastById(String cityId, ForecastByCityIdResponseListener forecast) {
        List<WeatherReportModel> weatherReportModels = new ArrayList<>();
        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityId;
        //get the json object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //   Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");
                    //get the first item in the array


                    for (int i = 0; i < consolidated_weather_list.length(); i++) {

                        WeatherReportModel one_day_weather = new WeatherReportModel();

                        JSONObject first_day_from_api = (JSONObject) consolidated_weather_list.get(0);

                        //setting data from api to own pojo
                        one_day_weather.setId(first_day_from_api.getInt("id"));
                        one_day_weather.setWeather_state_name(first_day_from_api.getString("weather_state"));
                        one_day_weather.setWeather_state_abbr(first_day_from_api.getString("weather_state_abbr"));
                        one_day_weather.setWind_direction_compass(first_day_from_api.getString("wind_direction_compass"));
                        one_day_weather.setWeather_created(first_day_from_api.getString("created"));
                        one_day_weather.setApplicable_date(first_day_from_api.getString("applicable_date"));
                        one_day_weather.setWeather_min_temp(first_day_from_api.getLong("min_temp"));
                        one_day_weather.setWeather_max_temp(first_day_from_api.getLong("max_temp"));
                        one_day_weather.setWeather_the_temp(first_day_from_api.getLong("the_temp"));
                        one_day_weather.setWeather_wind_speed(first_day_from_api.getLong("wind_speed"));
                        one_day_weather.setWeather_wind_direction(first_day_from_api.getLong("wind_direction"));
                        one_day_weather.setWeather_air_pressure(first_day_from_api.getInt("air_pressure"));
                        one_day_weather.setWeather_humidity(first_day_from_api.getInt("humidity"));
                        one_day_weather.setWeather_visibility(first_day_from_api.getLong("visibility"));
                        one_day_weather.setWeather_predictability(first_day_from_api.getString("predictability"));

                        weatherReportModels.add(one_day_weather);
                    }
                    forecast.onResponse(weatherReportModels);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                forecast.onError("Something went wrong");
            }
        });

        //get the property called "consolodated_weather" which is an array
        //get each item in the array and assign it to an new WeatherReportModel

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public interface getCityForecastByNameResponse{

        void onError(String msg);

        void onResponse(List<WeatherReportModel> weatherReportModelList);
    }
    @Override
    public void  getCityForecastByName(String cityName,getCityForecastByNameResponse getCityForecastByNameResponse) {
        getCityId(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String cityId) {
                //got the id
                getCityForecastById(cityId, new ForecastByCityIdResponseListener() {
                    @Override
                    public void onError(String msg) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModels) {
                        //we have the report
                        getCityForecastByNameResponse.onResponse(weatherReportModels);
                    }
                });
            }
        });
    }
}
