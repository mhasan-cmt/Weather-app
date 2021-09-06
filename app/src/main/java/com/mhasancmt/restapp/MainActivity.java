package com.mhasancmt.restapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mhasancmt.restapp.model.WeatherReportModel;
import com.mhasancmt.restapp.service.WeatherDataServiceImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnCityId,btnWeatherByCityId, btnWeatherByCityName;
    private EditText etCityInput;
    private ListView lvWeatherResult;
    final WeatherDataServiceImpl weatherDataService = new WeatherDataServiceImpl(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCityId=findViewById(R.id.btn_city_id);
        btnWeatherByCityId=findViewById(R.id.btn_weather_by_city);
        btnWeatherByCityName=findViewById(R.id.btn_weather_by_cityName);

        etCityInput=findViewById(R.id.et_cityName);

        lvWeatherResult = findViewById(R.id.lv_weather_result);

        //onclick listeners
        btnCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityId(etCityInput.getText().toString(),
                        new WeatherDataServiceImpl.VolleyResponseListener() {
                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String cityId) {
                        Toast.makeText(MainActivity.this, "Returned id is:"+cityId, Toast.LENGTH_SHORT).show();
                    }
                });

// Instantiate the RequestQueue.
//                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
// ueue.add(jsonArrayRequest);

//// Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//// Add the request to the RequestQueue.
//                queue.add(stringRequest);

            }
        });
        btnWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastByName(etCityInput.getText().toString(), new WeatherDataServiceImpl.getCityForecastByNameResponse() {

                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModelList) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                weatherReportModelList);
                        lvWeatherResult.setAdapter(arrayAdapter);

                    }
                });
            }
        });
        btnWeatherByCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastById(etCityInput.getText().toString(), new WeatherDataServiceImpl.ForecastByCityIdResponseListener() {
                    @Override
                    public void onError(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModelList) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                weatherReportModelList);
                        lvWeatherResult.setAdapter(arrayAdapter);

                    }
                });
            }
        });
    }
}