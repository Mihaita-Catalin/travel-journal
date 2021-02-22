package com.example.traveljournal.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {

    @SerializedName("weather")
    public ArrayList<Weather> weather;
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;

    public WeatherResponse(ArrayList<Weather> weather, Main main, Wind wind) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
    }
}
